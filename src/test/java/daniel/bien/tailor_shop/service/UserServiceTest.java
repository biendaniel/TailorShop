package daniel.bien.tailor_shop.service;

import daniel.bien.tailor_shop.model.user.User;
import daniel.bien.tailor_shop.service.user.UserService;
import daniel.bien.tailor_shop.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void shouldReturnTrueAutenticationData() {
        //given
        UserRepository userRepository = mock(UserRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        UserService userService = mock(UserService.class);
        User newUser = new User();
        newUser.setEmail("abc@wp.pl");
        newUser.setPassword("1qaz2wsx");
        newUser.setFirstname("1qaz2wsx");
        newUser.setLastname("1qaz2wsx");
        newUser.setPhoneNumber("1qaz2wsx");
        User user = userService.createUser(newUser);

        //when
        User newUser2 = new User();
        newUser2.setEmail("abc@wp.pl");
        newUser2.setPassword("1qaz2wsx");

        //then

        assertTrue(userService.checkAuthenticationData(newUser2, user));
    }



}