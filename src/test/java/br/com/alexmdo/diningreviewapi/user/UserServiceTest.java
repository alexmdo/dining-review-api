package br.com.alexmdo.diningreviewapi.user;

import br.com.alexmdo.diningreviewapi.user.dto.CreateUser;
import br.com.alexmdo.diningreviewapi.user.dto.UpdateUser;
import br.com.alexmdo.diningreviewapi.user.exceptions.UserAlreadyExistsException;
import br.com.alexmdo.diningreviewapi.user.exceptions.UserNotFoundException;
import br.com.alexmdo.diningreviewapi.user.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        User entity = new User();
        entity.setName("John Doe");
        entity.setCity("Cotia");
        entity.setState("SP");
        entity.setZipCode("06703798");
        entity.setInterestedInPeanutAllergies(true);
        entity.setInterestedInEggAllergies(false);
        entity.setInterestedInDairyAllergies(true);
        userRepository.save(entity);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void givenRegisterUser_whenUserDoesNotExists_thenSuccessfullyPersist() {
        CreateUser createUserNew = new CreateUser();
        createUserNew.setName("Doe John");
        createUserNew.setCity("Botucatu");
        createUserNew.setState("SP");
        createUserNew.setZipCode("12345678");
        createUserNew.setInterestedInPeanutAllergies(true);
        createUserNew.setInterestedInEggAllergies(true);
        createUserNew.setInterestedInDairyAllergies(true);

        CreateUser createUserRegistered = userService.registerUser(createUserNew);
        assertNotNull(createUserRegistered.getId());
        assertTrue(createUserRegistered.getId() > 0L);
        assertEquals("Doe John", createUserRegistered.getName());
        assertEquals("Botucatu", createUserRegistered.getCity());
        assertEquals("SP", createUserRegistered.getState());
        assertEquals("12345678", createUserRegistered.getZipCode());
        assertTrue(createUserRegistered.isInterestedInPeanutAllergies());
        assertTrue(createUserRegistered.isInterestedInEggAllergies());
        assertTrue(createUserRegistered.isInterestedInDairyAllergies());

        User userFound = userRepository.findById(createUserRegistered.getId()).orElseThrow();
        assertEquals(userFound.getId(), createUserRegistered.getId());
        assertEquals(userFound.getCity(), createUserRegistered.getCity());
        assertEquals(userFound.getState(), createUserRegistered.getState());
        assertEquals(userFound.getZipCode(), createUserRegistered.getZipCode());
        assertEquals(userFound.isInterestedInPeanutAllergies(), createUserRegistered.isInterestedInPeanutAllergies());
        assertEquals(userFound.isInterestedInEggAllergies(), createUserRegistered.isInterestedInEggAllergies());
        assertEquals(userFound.isInterestedInDairyAllergies(), createUserRegistered.isInterestedInDairyAllergies());
    }

    @Test
    void givenRegisterUser_whenUserExists_thenRaiseAnError() {
        CreateUser createUser = new CreateUser();
        createUser.setName("John Doe");
        assertThrows(UserAlreadyExistsException.class, () -> userService.registerUser(createUser), "Cannot register user with the same name");
    }

    @Test
    void givenUpdateUser_whenUserDoesNotExists_thenRaiseAnError() {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setId(999L);
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(updateUser), "User was not found given id 999");
    }

    @Test
    void givenUpdateUser_whenUserExists_thenUpdateSuccessfully() {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setId(1L);
        updateUser.setCity("Taubaté");
        updateUser.setState("SP");
        updateUser.setZipCode("010600123");
        updateUser.setInterestedInPeanutAllergies(false);
        updateUser.setInterestedInEggAllergies(true);
        updateUser.setInterestedInDairyAllergies(false);

        UpdateUser updateUserUpdated = userService.updateUser(updateUser);
        assertEquals(updateUser.getId(), updateUserUpdated.getId());
        assertEquals(updateUser.getCity(), updateUserUpdated.getCity());
        assertEquals(updateUser.getState(), updateUserUpdated.getState());
        assertEquals(updateUser.getZipCode(), updateUserUpdated.getZipCode());
        assertEquals(updateUser.isInterestedInPeanutAllergies(), updateUserUpdated.isInterestedInPeanutAllergies());
        assertEquals(updateUser.isInterestedInEggAllergies(), updateUserUpdated.isInterestedInEggAllergies());
        assertEquals(updateUser.isInterestedInDairyAllergies(), updateUserUpdated.isInterestedInDairyAllergies());

        User user = userRepository.findById(updateUserUpdated.getId()).orElseThrow();
        assertEquals(1L, user.getId());
        assertEquals("Taubaté", user.getCity());
        assertEquals("SP", user.getState());
        assertEquals("010600123", user.getZipCode());
        assertFalse(user.isInterestedInPeanutAllergies());
        assertTrue(user.isInterestedInEggAllergies());
        assertFalse(user.isInterestedInDairyAllergies());
    }

    @Test
    void findByName() {
    }

    @Test
    void validateUserSubmittedDiningReview() {
    }
}