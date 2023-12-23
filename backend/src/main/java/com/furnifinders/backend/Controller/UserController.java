package com.furnifinders.backend.Controller;

import com.furnifinders.backend.dto.Request.EditProfileRequest;
import com.furnifinders.backend.dto.Response.EditProfileResponse;
import com.furnifinders.backend.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/editProfile/{id}")
    public ResponseEntity<EditProfileResponse> editProfile(@RequestBody EditProfileRequest editProfileRequest, @PathVariable("id") Long id) {
        userService.editUser(id,editProfileRequest);
        EditProfileResponse userProfileResponse = new EditProfileResponse("Updated user profile successfully");
        return ResponseEntity.ok().body(userProfileResponse);
    }
}