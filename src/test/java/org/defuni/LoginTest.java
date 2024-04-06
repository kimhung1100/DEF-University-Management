package org.defuni;

import org.defuni.account.Manager;
import org.defuni.account.UserAccount;
import org.junit.Test;

import org.junit.Assert;


public class LoginTest {
    @Test
    public void testLogin() {
        Manager manager = Manager.getInstance();
        UserAccount result = manager.login("testUser", "password");

        Assert.assertTrue(result != null);

    }
}
