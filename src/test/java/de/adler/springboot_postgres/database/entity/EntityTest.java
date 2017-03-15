package de.adler.springboot_postgres.database.entity;

import org.junit.Assert;
import org.junit.Test;

public class EntityTest {
    @Test
    public void customerEqualsHashCodeTest() {
        Customer x = new Customer("Jack", "Bauer");
        Customer y = new Customer("Jack", "Bauer");
        Assert.assertTrue(x.equals(y) && y.equals(x));
        Assert.assertTrue(x.hashCode() == y.hashCode());
    }

    @Test
    public void userEqualsHashCodeTest() {
        User x = new User("Jack", "Bauer", "1@test.com", true);
        User y = new User("Jack", "Bauer", "1@test.com", true);
        Assert.assertTrue(x.equals(y) && y.equals(x));
        Assert.assertTrue(x.hashCode() == y.hashCode());
    }

    @Test
    public void userRoleEqualsHashCodeTest() {
        UserRole x = new UserRole(0L, "USER");
        UserRole y = new UserRole(0L, "USER");
        Assert.assertTrue(x.equals(y) && y.equals(x));
        Assert.assertTrue(x.hashCode() == y.hashCode());
    }
}