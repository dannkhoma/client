package com.nkhomad.service;

import com.nkhomad.BaseTest;
import com.nkhomad.model.UserDTO;
import com.nkhomad.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest extends BaseTest {

    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(userService, "userUrl", EXTERNAL_SERVICE_URL);
    }

    @Test
    public void whenUserIdAndUsernameAreNullThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.fetchUserByIdentifiers(null, null);
        });

        String expectedMessage = "Both id and username cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void whenUserIdIsNotNullAndUserNameIsNullReturnMatchingRecordById() {
        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(1L, null);

        assertEquals(user, map(getMockUser()));
    }

    @Test
    public void whenUserIdIsNotNullAndUsernameIsNullAndSearchByIdDoesNotMatchReturnRecordWithMinusOneId() {

        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(2L, null);

        assertEquals(user, UserDTO.builder()
                .id(-1L)
                .build());

    }
    @Test
    public void whenUsernameIsNotNullAndUserIdIsNullReturnMatchingRecordByUsername() {
        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(null, "Bret");

        assertEquals(user, map(getMockUser()));
    }

    @Test
    public void whenUsernameIsNotNullAndUserIdIsNullAndSearchByUsernameDoesNotMatchReturnRecordWithMinusOneId() {

        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(null, "Brett");

        assertEquals(user, UserDTO.builder()
                .id(-1L)
                .build());

    }

    @Test
    public void whenUsernameIsNotNullAndUserIdIsNotNullReturnMatchingRecord() {

        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(1L, "Bret");

        assertEquals(user, map(getMockUser()));

    }

    @Test
    public void whenUsernameIsNotNullAndUserIdIsNotNullReturnMatchingRecordByMatchingId() {

        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(1L, "Brett");

        assertEquals(user, map(getMockUser()));

    }

    @Test
    public void whenUsernameIsNotNullAndUserIdIsNotNullReturnMatchingRecordByMatchingUsername() {

        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(2L, "Bret");

        assertEquals(user, map(getMockUser()));

    }

    @Test
    public void whenUsernameIsNotNullAndUserIdIsNotNullAndNoMatchIsFoundReturnRecordWithMinusOneId() {

        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(2L, "Brett");

        assertEquals(user, UserDTO.builder()
                .id(-1L)
                .build());

    }
}