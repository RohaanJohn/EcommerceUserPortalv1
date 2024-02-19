package com.signify.user;
import java.util.Scanner;

public class UserInputReader {

    String username;
    String password;

    public void input() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Username:");
        String inUsername = myObj.next();
        System.out.println("Enter Password:");
        String inPassword = myObj.next();
        this.username = inUsername;
        this.password = inPassword;
    }

    public String readUsername(){
        return this.username;
    }

    public String readPassword(){
        return this.password;
    }

}
