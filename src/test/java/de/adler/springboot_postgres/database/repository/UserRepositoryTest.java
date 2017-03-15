package de.adler.springboot_postgres.database.repository;

import de.adler.springboot_postgres.database.entity.User;
import de.adler.springboot_postgres.database.entity.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;

@SuppressWarnings("CanBeFinal")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @SuppressWarnings("WeakerAccess")
    @Autowired
    UserRepository userRepository;

    @SuppressWarnings("WeakerAccess")
    @Autowired
    UserRoleRepository userRoleRepository;

    @Test(expected = DataIntegrityViolationException.class)
    @DirtiesContext
    public void saveDuplicateUserTest() throws Exception {
        User newUser = new User("Jack", "Bauer", "1@test.com", true);
        userRepository.save(newUser);
        try {
            userRepository.save(new User("Kim", "Bauer", "1@test.com", true));
            Assert.fail("expecting DataIntegrityViolationException here");
        } catch (Exception e) {
            User bauer = userRepository.findByEmail("1@test.com");
            Assert.assertThat(bauer, is(newUser));
            throw e;
        }
    }

    @Test(expected = DataIntegrityViolationException.class)
    @DirtiesContext
    public void saveDuplicateUserRoleTest() throws Exception {
        User newUser = new User("Jack", "Bauer", "1@test.com", true);
        userRepository.save(newUser);
        UserRole newRole = new UserRole(newUser.getUserid(), "ADMIN");
        userRoleRepository.save(newRole);
        try {
            userRoleRepository.save(new UserRole(newUser.getUserid(), "ADMIN"));
            Assert.fail("expecting DataIntegrityViolationException here");
        } catch (Exception e) {
            UserRole role = userRoleRepository.findByUserId(newUser.getUserid());
            Assert.assertThat(role, is(newRole));
            throw e;
        }
    }
}