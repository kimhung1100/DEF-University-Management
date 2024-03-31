package org.defuni.account;

import org.defuni.account.UserAccount;
import org.defuni.account.UserAccountType;

import java.time.LocalDate;

public abstract class UserAccountFactory {
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate dob;

    abstract UserAccount createUser();
}
