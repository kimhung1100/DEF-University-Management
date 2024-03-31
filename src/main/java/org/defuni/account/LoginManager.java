package org.defuni.account;

public class LoginManager {
    private static LoginManager instance;

    private LoginManager() {
        // Prevent instantiation via reflection
        if (instance != null) {
            throw new IllegalStateException("Cannot instantiate singleton class using reflection");
        }
    }
    public static synchronized LoginManager getInstance() {
        if (instance == null) {
            instance = new LoginManager();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        // Dummy implementation, replace with actual authentication logic
        // Here you can implement your authentication logic, e.g., check against a database
        if ("admin".equals(username) && "password".equals(password)) {
            return true;
        } else {
            return false;
        }
    }


}
