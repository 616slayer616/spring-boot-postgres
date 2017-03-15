package de.adler.springboot_postgres.database.repository;

import de.adler.springboot_postgres.database.entity.Customer;
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
public class CustomerRepositoryTest {

    @SuppressWarnings("WeakerAccess")
    @Autowired
    CustomerRepository repository;

    @Test
    @DirtiesContext
    public void findByLastName_2Test() throws Exception {
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));

        Customer bauerRef = repository.findByLastName("Bauer");
        Customer bauer = repository.findByLastNameCaseInsensitive("Bauer");
        Assert.assertThat(bauer, is(bauerRef));
    }

    @Test(expected = DataIntegrityViolationException.class)
    @DirtiesContext
    public void saveDuplicateTest() throws Exception {
        Customer jackBauer = new Customer("Jack", "Bauer");
        repository.save(jackBauer);
        try {
            repository.save(new Customer("Kim", "Bauer"));
            Assert.fail("expecting DataIntegrityViolationException here");
        } catch (Exception e) {
            Customer bauer = repository.findByLastName("Bauer");
            Assert.assertThat(bauer, is(jackBauer));
            throw e;
        }
    }
}