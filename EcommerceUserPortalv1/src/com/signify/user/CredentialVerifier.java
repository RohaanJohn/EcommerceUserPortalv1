package com.signify.user;

public class CredentialVerifier {
    public static int verifyCredentials (String enteredUsername, String enteredPassword, String
            storedUsername, String storedPassword){
        if (enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword)) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
