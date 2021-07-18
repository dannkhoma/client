package com.nkhomad.repository;

import com.nkhomad.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final RestTemplate restTemplate;

    @Override
    public List<User> fetchUsers(final String userUrl) {

        log.info("Fetching user information from url {}, restTemplate {}", userUrl, restTemplate);

        if (userUrl == null) {
            throw new IllegalArgumentException("Url is null");
        }

        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(URI.create(userUrl), User[].class);


        log.debug("User information response body {}", responseEntity);

        if (responseEntity.hasBody()) {
            return Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        }

        return Collections.emptyList();
    }
}
