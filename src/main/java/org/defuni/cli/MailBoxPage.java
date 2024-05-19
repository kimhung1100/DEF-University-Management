package org.defuni.cli;

import org.defuni.account.*;
import org.defuni.course.*;

import java.util.*;

public class MailBoxPage {
    UserAccount user;

    MailBoxPage(UserAccount user) {
        this.user = user;
        this.run();
    }

    public void run() {
        clearScreen();
        List<String> notifications = user.getNotifications();

        System.out.println("Your mailbox:");
        for (String notification : notifications) {
            System.out.println(notification);
        }

        System.out.print("*Press Enter to exit");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        clearScreen();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
