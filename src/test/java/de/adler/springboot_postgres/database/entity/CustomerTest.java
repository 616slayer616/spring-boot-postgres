package de.adler.springboot_postgres.database.entity;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    @Test
    public void testEqualsHashCode() {
        Customer x = new Customer("Jack", "Bauer");
        Customer y = new Customer("Jack", "Bauer");
        Assert.assertTrue(x.equals(y) && y.equals(x));
        Assert.assertTrue(x.hashCode() == y.hashCode());
    }
}