package com.signify.user;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartMain{
    public static void main(String[] args) {

        CredentialsReader readerFirst = new CredentialsReader();
        readerFirst.credentials();
        String trueUserName = readerFirst.getUsername();
        String truePassWord = readerFirst.getPassWord();
        UserInputReader readerSecond = new UserInputReader();
        readerSecond.input();
        String username = readerSecond.readUsername();
        String password = readerSecond.readPassword();
        CredentialVerifier readerThird = new CredentialVerifier();

        int flag = readerThird.verifyCredentials(username, password, trueUserName, truePassWord);

        if(flag == 0){
            System.out.println("Authentication Failed!");
        }
        else if(flag == 1) {

            Scanner myObj = new Scanner(System.in);
            Item[] items = {
                    new Item("1", "SamsungM51", 20000, 12),
                    new Item("2", "SamsungM52", 21000, 13),
                    new Item("3", "SamsungM53", 22000, 14),
                    new Item("4", "SamsungM54", 23000, 15),
            };
            int ch;
            ArrayList<Integer> listId = new ArrayList<Integer>(100);;
            System.out.print("Hello " + username + "!");
            System.out.println("\n");
            String name = username;
            System.out.print("Enter cart size: ");
            int cartSize = myObj.nextInt();
            OnlineShoppingCart shoppingCart = new OnlineShoppingCart(name, cartSize);
            do {
                System.out.println("\nPlease Select\n1. Menu\n2. Add an item to cart\n3. Remove an item from cart\n4. Modify Cart (Change quantity of an item)\n5. View Cart\n6. Checkout\n7. Exit");
                ch = myObj.nextInt();
                switch (ch) {
                    case 1:
                        System.out.println("Menu:");
                        for (int i = 0; i < items.length; i++) {
                            System.out.println((items[i].getId() + " " + items[i].getName() + " " + items[i].getPrice() + " " + items[i].getStock()));
                        }
                        break;
                    case 2:
                        System.out.println("Menu:");
                        int selectedItem = 0;
                        int quantity = 0;
                        for (int i = 0; i < items.length; i++) {
                            System.out.println((items[i].getId() + " " + items[i].getName() + " " + items[i].getPrice() + " " + items[i].getStock()));
                        }

                        System.out.println("Select an Item: ");
                        selectedItem = myObj.nextInt();

                        System.out.println("How much quantity of the item would you like: ");
                        quantity = myObj.nextInt();

                        if(quantity > items[(selectedItem - 1)].getStock()){
                            System.out.println("Not enough in stock!");
                            break;
                        }

                        else if (selectedItem > items.length) {
                            System.out.println("Invalid item.");
                            break;
                        }
                        try {
                            int tempFlag = 0;
                            for(int i = 0; i < listId.size(); i++){
                                if(listId.get(i) == selectedItem){
                                    tempFlag = 1;
                                    break;
                                }
                            }
                            System.out.println(tempFlag);
                            if(tempFlag == 1) {
                                String strSelectedItem = Integer.toString(selectedItem);
                                shoppingCart.updateItem(strSelectedItem, quantity);
                                int newStock = items[(selectedItem - 1)].getStock() - quantity;
                                items[(selectedItem - 1)].setStock(newStock);
                                System.out.println(quantity + " more " + items[(selectedItem - 1)].getName() + " added to cart.");
                                System.out.println(listId);
                            }
                            else {
                                listId.add(selectedItem);
                                shoppingCart.addItem(items[(selectedItem - 1)], quantity);
                                int newStock = items[(selectedItem - 1)].getStock() - quantity;
                                items[(selectedItem - 1)].setStock(newStock);
                                System.out.println(quantity + " " + items[(selectedItem - 1)].getName() + " added to cart.");
                                System.out.println(listId);
                            }
                        } catch (ShoppingCartException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            shoppingCart.view();
                            System.out.print("Enter item ID to remove: ");
                            String itemId = myObj.next();
                            try {
                                int intItemId = Integer.parseInt(itemId);
                                int tempStock = items[(intItemId-1)].getStock();
                                int tempQuantity = shoppingCart.getQuantity(intItemId-1);
                                items[intItemId-1].setStock(tempStock+tempQuantity);
                                shoppingCart.removeItem(itemId);
                                for(int i=0; i<listId.size(); i++){
                                    if (listId.get(i) == intItemId) {
                                        listId.remove(i);
                                    }
                                    }
                                System.out.println(listId);
                            } catch (ShoppingCartException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        } catch (ShoppingCartException e) {
                            System.out.println(e.getMessage());
                        }
                    case 4:
                        try {
                            shoppingCart.view();
                            System.out.print("Enter item ID to update: ");
                            String itemId = myObj.next();
                            System.out.println("Enter the new quantity:");
                            Integer newQuantity = myObj.nextInt();
                            shoppingCart.modifyItem(itemId, newQuantity );
                        } catch (ShoppingCartException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            shoppingCart.view();
                        } catch (ShoppingCartException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 6:
                        try {
                            shoppingCart.checkout();
                        } catch (ShoppingCartException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                }
            } while (ch != 7);
        }
    }
}
