package com.signify.user;
import java.util.ArrayList;
import java.util.Dictionary;

class ShoppingCartException extends Exception{
    ShoppingCartException(String msg){
        super(msg);
    }
}

public class ShoppingCart {
    ArrayList<Item> items;
    ArrayList<Integer> quantities;
    int itemCount = 0;
    int cartSize = 0;
    double totalPrice;

    ShoppingCart(int size){
        items = new ArrayList<>(size);
        quantities = new ArrayList<Integer>(size);
        cartSize = size;
    }

    int getQuantity(int itemID){
       return quantities.get(itemID);
    }

    void addItem(Item item, int quantity) throws ShoppingCartException{
        if(item == null)
            throw new ShoppingCartException("Null Item!");
        if(item.getStock() <= 0)
            throw new ShoppingCartException("Item is Empty!");
        if(cartSize == items.size())
            throw  new ShoppingCartException("Cart is full!");
        items.add(item);
        quantities.add(quantity);
        itemCount++;
    }

    void updateItem(String itemID, int givenQuantity){
        int i;
        int oldQuantity;

        if (quantities.isEmpty()) {
            oldQuantity = 0;
        } else {
            oldQuantity = quantities.get(Integer.parseInt(itemID) - 1);
        }

        int newQuantity = oldQuantity + givenQuantity;

        for (i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(itemID)) {
                System.out.println(items.get(i).getName() + " purchase details updated!");
                quantities.add(i, newQuantity);
                break;
            }
        }
    }

    void modifyItem(String itemID, int givenQuantity){
        int i;
        int newQuantity = givenQuantity;
        for(i=0; i<items.size(); i++){
            if(items.get(i).getId().equals(itemID)){
                System.out.println(items.get(i).getName()+ " purchase details updated!");
                quantities.add(i, newQuantity);
                break;
            }
        }
    }

    void removeItem(String itemID) throws ShoppingCartException{
        int i;
        if(itemCount == 0)
            throw new ShoppingCartException("Cart is empty!");
        for(i=0; i<items.size(); i++){
            if(items.get(i).getId().equals(itemID)){
                System.out.println(items.get(i).getName()+ " removed!");
                items.remove(i);
                quantities.remove(i);
                itemCount --;

                break;
            }
        }

        if (i == itemCount)
            throw new ShoppingCartException("Item not found!");
    }
    void checkout() throws ShoppingCartException{
        if(itemCount == 0)
            throw new ShoppingCartException("Cart is empty!");
        System.out.println("Loading");
    }

    void view() throws ShoppingCartException{
        if(itemCount == 0)
            throw new ShoppingCartException("Cart is empty!");
        System.out.println("Loading");
    }

}
