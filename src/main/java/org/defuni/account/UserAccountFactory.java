package org.defuni.account;

import org.defuni.account.UserAccount;
import org.defuni.account.UserAccountType;

import java.time.LocalDate;

public abstract class UserAccountFactory {
    public abstract UserAccount createUser(UserAccountType type);

}
