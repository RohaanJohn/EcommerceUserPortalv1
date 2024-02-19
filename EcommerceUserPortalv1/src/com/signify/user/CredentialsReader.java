package com.signify.user;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class CredentialsReader {
    String userName;
    String passWord;

    public void credentials() {
        String filePath = "C:\\Users\\670310849\\Downloads\\folders\\Documents\\Sample.txt";
        BufferedReader inFile = null;
        try {
            inFile = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String sample = "";
        while (true) {
            try {
                if ((sample = inFile.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String str = sample;
            String[] tempArray = str.split(", ");
            String userNameTrue = tempArray[0];
            String passWordTrue = tempArray[1];

            this.userName = userNameTrue;
            this.passWord = passWordTrue;
        }
    }

    public String getUsername() {
        return this.userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

}
