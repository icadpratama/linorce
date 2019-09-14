package com.lawencon.linov.outsource.controller.authentication;

import com.lawencon.linov.outsource.exception.ResourceNotFoundException;
import com.lawencon.linov.outsource.model.authentication.User;
import com.lawencon.linov.outsource.payload.response.UserIdentityAvailability;
import com.lawencon.linov.outsource.payload.response.UserProfile;
import com.lawencon.linov.outsource.payload.response.UserSummary;
import com.lawencon.linov.outsource.repository.ItemRequestRepository;
import com.lawencon.linov.outsource.security.CurrentUser;
import com.lawencon.linov.outsource.security.UserPrincipal;
import com.lawencon.linov.outsource.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ItemRequestRepository requestRepository;

    public UserController(UserService userService, ItemRequestRepository requestRepository) {
        this.userService = userService;
        this.requestRepository = requestRepository;
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

//    @PutMapping
//    public

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userService.userListByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long requestCount = requestRepository.countByCreatedBy(user.getId());

        return new UserProfile(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getCreatedAt(), requestCount);
    }

}
