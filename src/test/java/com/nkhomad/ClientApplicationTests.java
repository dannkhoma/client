package com.nkhomad;

import com.nkhomad.repository.UserRepository;
import com.nkhomad.rest.UserRestController;
import com.nkhomad.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRestController userRestController;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(userService);
        Assertions.assertNotNull(userRepository);
        Assertions.assertNotNull(userRestController);
    }

}
