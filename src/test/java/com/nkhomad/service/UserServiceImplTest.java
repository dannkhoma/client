package com.nkhomad.service;

import com.nkhomad.Util;
import com.nkhomad.model.UserDTO;
import com.nkhomad.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest extends Util {

    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(userService, "userUrl", EXTERNAL_SERVICE_URL);
    }

    @Test
    void whenUserIdAndUsernameAreNullThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.fetchUserByIdentifiers(null, null);
        });

        String expectedMessage = "Both id and username cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @ParameterizedTest
    @MethodSource("provideParameters")
    void whenDifferentParameterCombinationsAreUsedReturnMatchingRecord(final Long id, final String username) {
        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(id, username);

        assertEquals(user, map(getMockUser()));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidParameters")
    void whenUserParametersDoNotMatchAnyRecordReturnRecordWithMinusOneId(final Long id, final String username) {

        Mockito
                .when(userRepository.fetchUsers(EXTERNAL_SERVICE_URL))
                .thenReturn(Arrays.asList(getUsers()));

        final UserDTO user = userService.fetchUserByIdentifiers(id, username);

        assertEquals(user, UserDTO.builder()
                .id(-1L)
                .build());

    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.of(1L, null),
                Arguments.of(null, "Bret"),
                Arguments.of(1L, "Bret"),
                Arguments.of(1L, "Brett"),
                Arguments.of(2L, "Bret")
        );
    }

    private static Stream<Arguments> provideInvalidParameters() {
        return Stream.of(
                Arguments.of(2L, null),
                Arguments.of(null, "Brett"),
                Arguments.of(2L, "Brett")
        );
    }
}