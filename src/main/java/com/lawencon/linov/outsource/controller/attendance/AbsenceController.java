package com.lawencon.linov.outsource.controller.attendance;

import com.lawencon.linov.outsource.exception.ResourceNotFoundException;
import com.lawencon.linov.outsource.model.attendance.Absence;
import com.lawencon.linov.outsource.payload.request.AbsenceRequest;
import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.AbsenceService;
import com.lawencon.linov.outsource.util.AbsenceType;
import com.lawencon.linov.outsource.util.CommonUtil;
import com.lawencon.linov.outsource.util.PageAndSort;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping("/absence")
public class AbsenceController {

    private final AbsenceService absenceService;
    private final Logger logger = LoggerFactory.getLogger(AbsenceController.class);

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HR')")
    public ResponseEntity historyCheckInByUser(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(absenceService.getAbsenceById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_ADMIN')")
    public ResponseEntity allHistoryCheckIn(@Valid PageAndSort model){
        Page result = absenceService.getAllAbsences(model);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/checkin")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity checkIn(@Valid @RequestBody AbsenceRequest absenceRequest,
            @CurrentUser UserPrincipal currentUser) {
        Absence absence = new Absence(absenceRequest.getLocation(), absenceRequest.getProjectName(), absenceRequest.getActivity(), CommonUtil.currentMonth(), CommonUtil.currentYear(), AbsenceType.CHECK_IN);
        Absence result = absenceService.checkIn(absence);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/checkout")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HR')")
    public ResponseEntity checkOut(@CurrentUser UserPrincipal currentUser) {
        Absence absence = absenceService.getAbsenceByIdContaining(currentUser.getId(), CommonUtil.resetTimeStart(), CommonUtil.resetTimeEnd()).orElseThrow(
                ()-> new ResourceNotFoundException("Absence","id",currentUser.getId()));
        absence.setUpdatedAt(Instant.now());
        absence.setEnd(new Timestamp(System.currentTimeMillis()));
        absence.setType(AbsenceType.CHECK_OUT);
        final Absence update = absenceService.checkIn(absence);
        return ResponseEntity.ok(update);
    }

    @GetMapping("/download/excel")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HR')")
    public ResponseEntity downloadReport(
            @CurrentUser UserPrincipal currentUser){
        String[] columns = {"No", "Date", "Name of Project", "Location", "Start", "-" , "End", "Hour", "Activity/Remark"};

        try (XSSFWorkbook workbook = new XSSFWorkbook()){

            CreationHelper createHelper = workbook.getCreationHelper();
            XSSFSheet sheet = workbook.createSheet("Absence");

            CellStyle csCenter = workbook.createCellStyle();
            centerText(csCenter);
            styleTable(csCenter);

            // First Row Name and Job
            Font firstFont = workbook.createFont();
            firstFont.setColor(IndexedColors.WHITE.getIndex());

            CellStyle firstCellStyle = workbook.createCellStyle();
            firstCellStyle.setFont(firstFont);
            firstCellStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
            firstCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            centerText(firstCellStyle);
            styleTable(firstCellStyle);

            Row firstRow = sheet.createRow((short)0);
            Cell cellName = firstRow.createCell((short)0);
            cellName.setCellValue("NAME");
            cellName.setCellStyle(firstCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,1));
            Cell cellNameValue = firstRow.createCell(2);
            cellNameValue.setCellValue(currentUser.getFirstName() + " "+ currentUser.getLastName());
            cellNameValue.setCellStyle(csCenter);
            sheet.addMergedRegion(new CellRangeAddress(0,0, 2, 3));

            Cell cellJob = firstRow.createCell((short) 4);
            cellJob.setCellValue("JOB");
            cellJob.setCellStyle(firstCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0,0,4,6));
            Cell cellJobName = firstRow.createCell((short) 7);
            cellJobName.setCellValue("Full Stack Developer");
            cellJobName.setCellStyle(csCenter);

            Cell lawencon = firstRow.createCell((short) 8);
            lawencon.setCellValue("PT LAWENCON INTERNASIONAL");
            lawencon.setCellStyle(csCenter);
            sheet.addMergedRegion(new CellRangeAddress(0,0,8,9));

            // Header
            Font headerFont = workbook.createFont();
            headerFont.setColor(IndexedColors.BLACK.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            centerText(headerCellStyle);
            styleTable(headerCellStyle);

            // Row for header
            Row headerRow = sheet.createRow(1);
            Row secondRow = sheet.createRow(2);
            Row thirdRow = sheet.createRow(3);
            for (int col = 0; col < columns.length; col++) {
                Cell cell;
                if (col <= 3 || col == 8) {
                    cell = headerRow.createCell(col);
                    cell.setCellValue(columns[col].toUpperCase());
                    cell.setCellStyle(headerCellStyle);
                    styleTable(headerCellStyle);

                    if (col == 8) {
                        sheet.addMergedRegion(new CellRangeAddress(1, 4, 8,9));
                    } else {
                        sheet.addMergedRegion(new CellRangeAddress(1, 4, col,col));
                    }
                }

                if (col > 3 && col < 8) {
                    cell = headerRow.createCell(4);
                    cell.setCellValue("");

                    cell = secondRow.createCell(4);
                    cell.setCellValue("WORKING HOURS");
                    cell.setCellStyle(firstCellStyle);
                    styleTable(firstCellStyle);

                    cell = secondRow.createCell(7);
                    cell.setCellValue("TOTAL");
                    cell.setCellStyle(firstCellStyle);
                    styleTable(firstCellStyle);

                    cell = thirdRow.createCell(col);
                    cell.setCellValue(columns[col].toUpperCase());
                    cell.setCellStyle(headerCellStyle);
                    styleTable(headerCellStyle);
                    sheet.addMergedRegion(new CellRangeAddress(3, 4, col,col));
                }
            }
            sheet.addMergedRegion(new CellRangeAddress(1,1,4,7));
            sheet.addMergedRegion(new CellRangeAddress(2,2,4,6));
            // e5b8b8 color for holiday

            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
            centerText(dateCellStyle);

            CellStyle timeCellStyle = workbook.createCellStyle();
            timeCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("HH:mm"));
            centerText(timeCellStyle);

            CellStyle hourCellStyle = workbook.createCellStyle();
            hourCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("HH"));
            centerText(hourCellStyle);

            int rowIdx = 5;
            int no = 1;

            List<Absence> absences = absenceService.getAllAbsencePerMonth(currentUser.getId(), CommonUtil.currentMonth(), CommonUtil.currentYear());
            for (Absence absence: absences) {
                Row row = sheet.createRow(rowIdx);

                Cell noCell = row.createCell(0);
                noCell.setCellValue(no++);
                noCell.setCellStyle(csCenter);
                styleTable(csCenter);

                Cell dateCell = row.createCell(1);
                dateCell.setCellValue(Date.from(absence.getCreatedAt()));
                dateCell.setCellStyle(dateCellStyle);
                styleTable(dateCellStyle);

                Cell projectName = row.createCell(2);
                projectName.setCellValue(absence.getProjectName());
                projectName.setCellStyle(csCenter);
                styleTable(csCenter);

                Cell location = row.createCell(3);
                location.setCellValue(absence.getLocation());
                location.setCellStyle(csCenter);
                styleTable(csCenter);

                Cell start = row.createCell(4);
                start.setCellValue(absence.getStart());
                start.setCellStyle(timeCellStyle);
                styleTable(timeCellStyle);

                Cell strip = row.createCell(5);
                strip.setCellValue("");
                strip.setCellStyle(csCenter);
                styleTable(csCenter);

                Cell end = row.createCell(6);
                end.setCellValue(absence.getEnd());
                end.setCellStyle(timeCellStyle);
                styleTable(timeCellStyle);

                Cell total = row.createCell(7);
                String ref = "G" +(rowIdx+1)+ "-E" + (rowIdx+1);
                total.setCellFormula(ref);
                total.setCellStyle(hourCellStyle);
                styleTable(hourCellStyle);

                Cell activity = row.createCell(8);
                activity.setCellValue(absence.getActivity());
                activity.setCellStyle(csCenter);
                sheet.addMergedRegion(new CellRangeAddress(rowIdx, rowIdx, 8,9));
                setBordersToMergedCells(sheet);

                rowIdx++;
            }

            sheet.setColumnWidth(0, width(3));
            sheet.setColumnWidth(1, width(17));
            sheet.setColumnWidth(2, width(30));
            sheet.setColumnWidth(3, 19);
            sheet.setColumnWidth(4, width(9));
            sheet.setColumnWidth(5, width(1));
            sheet.setColumnWidth(6, width(9));
            sheet.setColumnWidth(7, width(cellJobName.getStringCellValue().length()));
            sheet.setColumnWidth(8, width(14));
            sheet.setColumnWidth(9, width(23));

            String fileName = "Timesheet_"+ CommonUtil.trimAllSpaces(currentUser.getFirstName()+" "+currentUser.getLastName()+"_"+CommonUtil.getMonth()+"_"+CommonUtil.currentYear(), "-");

            FileOutputStream fos = new FileOutputStream(new File(fileName+".xlsx"));
            workbook.write(fos);
            fos.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    private static int width(int i){
        return 256 * i;
    }
    private static void styleTable(CellStyle style){
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
    }
    private static void centerText(CellStyle style){
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    private static void setBordersToMergedCells(Sheet sheet) {
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress rangeAddress : mergedRegions) {
            RegionUtil.setBorderTop(BorderStyle.THIN, rangeAddress, sheet);
            RegionUtil.setBorderLeft(BorderStyle.THIN, rangeAddress, sheet);
            RegionUtil.setBorderRight(BorderStyle.THIN, rangeAddress, sheet);
            RegionUtil.setBorderBottom(BorderStyle.THIN, rangeAddress, sheet);
        }
    }
}
