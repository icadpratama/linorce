package com.lawencon.linov.outsource.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagingAndSorting {

    public static Pageable paging(PageAndSort model){

        Sort.Direction direct = null;

        if (CommonUtil.isEmpty(model.getDirection())) {
            direct = Sort.Direction.ASC;
        }

        if (model.getPage() == 0) {
            model.setPage(0);
        }

        if (model.getSize() <= 0) {
            model.setSize(5);
        }

        return PageRequest.of(model.getPage(), model.getSize(), direct, model.getColumn());
    }
}
