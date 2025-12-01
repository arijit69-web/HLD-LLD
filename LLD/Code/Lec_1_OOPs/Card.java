package Lec_1_OOPs;

abstract public class Card implements PaymentMethod {

    private String cardNo;
    private String userName;

    public Card(String cardNo, String userName)
    {
        this.cardNo = cardNo;
        this.userName = userName;
    }
    // These are private properties you cannot directly read them, so you need getters and setters
    public String getCardNo()
    {
        return  this.cardNo;
    }

    public String getUserName()
    {
        return  this.userName;
    }

    /*
    public void pay()
    {
    We don’t know how to implement this method in the Card class, so we must declare it as abstract.
    That also makes the class abstract, which means you cannot create an object of this class.
    }
    */

    /* Since the method is abstract, it must be implemented by any child class that extends this class.
    //abstract void pay();
    We don't need pay() method here... Why?
    There are 2 type of cards CC and DC, but UPI and Wallet are not type of cards, but we still want everyone should have a pay() method.
    We have to define this contract in the interface : PaymentMethod.
    PaymentMethod is an interface that all these 4 classes will implement
    */


}
