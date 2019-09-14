package com.lawencon.linov.outsource.controller.authentication;

import com.lawencon.linov.outsource.exception.ResourceNotFoundException;
import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.payload.response.UserIdentityAvailability;
import com.lawencon.linov.outsource.payload.response.UserProfile;
import com.lawencon.linov.outsource.payload.response.UserSummary;
import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.ImageService;
import com.lawencon.linov.outsource.service.ItemRequestService;
import com.lawencon.linov.outsource.service.UserService;
import com.lawencon.linov.outsource.util.CommonUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ItemRequestService requestService;
    private final ImageService imageService;

    public UserController(UserService userService, ItemRequestService requestService, ImageService imageService) {
        this.userService = userService;
        this.requestService = requestService;
        this.imageService = imageService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HR')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(), currentUser.getLastName());
    }

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = userService.checkUserByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = userService.checkUserByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @PostMapping("/profile")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_HR')")
    public ResponseEntity updateProfilePicture(
            @RequestParam(name = "file") MultipartFile file,
            @CurrentUser UserPrincipal currentUser) throws IOException {

        String fileName = "avatar." + CommonUtil.getFileExtension(file);
        System.out.println(fileName);
        String objectName = fileName;
        String bucketName = currentUser.getUsername() + "/" + "avatar";
        String contentType = file.getContentType();

        Long size = file.getSize();
        InputStream data = file.getInputStream();

        CommonUtil.fileUpload(bucketName, objectName, data, size, contentType);
        Image image = imageService.uploadImage(new Image(objectName, bucketName, size, contentType));
        return ResponseEntity.ok(image);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userService.userListByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long requestCount = requestService.sumOfRequest(user.getId());

        return new UserProfile(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getCreatedAt(), requestCount);
    }

}
