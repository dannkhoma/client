package com.nkhomad.service;

import com.nkhomad.model.User;
import com.nkhomad.model.UserDTO;
import com.nkhomad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Value("${users.url}")
    private String userUrl;
    private static final String USER_NOT_FOUND_ID = "-1";

    @Override
    public UserDTO fetchUserByIdentifiers(final Long id, String username) {

        validateParameters(id, username);

        log.debug("Fetching user information by parameters id {} or username {}", id, username);

        Optional<UserDTO> optionalUserDTO = userRepository.fetchUsers(userUrl)
                .stream()
                .filter(user ->
                        isUserIdEqual(id, user.getId()) || isUserNameEqual(username, user.getUsername())
                )
                .map(this::map)
                .findFirst();

        return optionalUserDTO.orElseGet(() -> UserDTO.builder()
                .id(Long.valueOf(USER_NOT_FOUND_ID))
                .build());

    }

    private void validateParameters(Long id, String username) {
        if (id == null && username == null) {
            throw new IllegalArgumentException("Both id and username cannot be null");
        }
    }

    private boolean isUserIdEqual(Long givenId, Long userId) {
        return givenId != null && Objects.equals(givenId, userId);
    }

    private boolean isUserNameEqual(String givenUsername, String username) {
        return givenUsername != null && givenUsername.equalsIgnoreCase(username);
    }

    private UserDTO map(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }
}
