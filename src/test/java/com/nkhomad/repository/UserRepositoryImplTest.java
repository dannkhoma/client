package com.nkhomad.repository;

import com.nkhomad.BaseTest;
import com.nkhomad.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImplTest extends BaseTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
    public void whenUserUrlIsNullThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> userRepository.fetchUsers(null));

        String expectedMessage = "Url is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void whenUserUrlIsValidReturnListOfUsers() {
        Mockito
                .when(restTemplate.getForEntity(URI.create("https://jsonplaceholder.typicode.com/users"), User[].class))
                .thenReturn(new ResponseEntity(getUsers(), HttpStatus.OK));

        List<User> users = userRepository.fetchUsers("https://jsonplaceholder.typicode.com/users");
        Assertions.assertEquals(Arrays.asList(getUsers()), users);
    }


}
