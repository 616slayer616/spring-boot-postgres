package de.adler.springboot_postgres.database.repository;

import de.adler.springboot_postgres.database.entity.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
    public void findByLastName_2Test() throws Exception {
        // save a couple of customers
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));

        List<Customer> bauerListRef = repository.findByLastName("Bauer");
        List<Customer> bauerList = repository.findByLastNameCaseInsensitive("Bauer");
        Assert.assertThat(bauerList, is(bauerListRef));
    }

}