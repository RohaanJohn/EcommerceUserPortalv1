package com.signify.user;

public class OnlineShoppingCart extends ShoppingCart{
    OnlineShoppingCart(String name, int cartSize){
        super(cartSize);
        this.name = name;
    }

    private String name;

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    double calculateTotalPrice(){
        double total = 0;
        for(int i=0;i < itemCount;i++){
            total+=items.get(i).getPrice()*quantities.get(i);
        }
        return total;
    }

    void checkout() throws ShoppingCartException{
        super.checkout();
        System.out.println("Name: " + getUserName() + "\nItems: ");
        for(int i=0;i<itemCount;i++)
            System.out.print("Item: " + items.get(i).getName() + " " + "Quantity: " + quantities.get(i) + "\n");
        System.out.println("The Total Price is: " + calculateTotalPrice());
    }

    void view() throws ShoppingCartException{
        super.view();
        System.out.println("Name: " + getUserName() + "\nItems: ");
        for(int i=0;i<itemCount;i++)
            System.out.print("Item ID:" + " " + items.get(i).getId() + " " + "Item Name: " + items.get(i).getName() + " " +  "Item Quantity: " + quantities.get(i) + "\n");
        System.out.println("The Total Price so far is: " + calculateTotalPrice());
    }
}
