package com.nkhomad.rest;

import com.nkhomad.model.UserDTO;
import com.nkhomad.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private static final String ERROR_ID = "-2";

    @GetMapping
    public UserDTO fetchUser(@RequestParam(value = "id", required = false) final Long id,
                             @RequestParam(value = "username", required = false) final String username) {
        try {
            log.info("Received fetch user request, parameters id {} , username {}", id, username);
            final UserDTO userDTO = userService.fetchUserByIdentifiers(id, username);
            log.info("Response for fetch user is {}", userDTO);
            return userDTO;
        } catch (Exception exception) {
            log.error("An error occurred while fetching user details. The exception details are: ", exception);
            return UserDTO.builder()
                    .id(Long.valueOf(ERROR_ID))
                    .build();
        }
    }
}
