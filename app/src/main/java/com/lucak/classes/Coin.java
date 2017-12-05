package com.lucak.classes;

/**
 * Created by lkaastra6885 on 11/27/2017.
 */

public class Coin {
    private int id;
    private int user_id;
    private double coin_Amount;
    private double bought_Price;
    private String coin_Ticker;
    private String coin_Name;

    public Coin(){
        id = 0;
        user_id = 0;
        coin_Amount = 0;
        bought_Price = 0;
        coin_Name = "";
        coin_Ticker = "";
    }

    public Coin(int user_id, double coin_Amount, double bought_Price, String coin_Ticker, String coin_Name){
        this.id = 0;
        this.user_id = user_id;
        this.coin_Amount = coin_Amount;
        this.bought_Price = bought_Price;
        this.coin_Name = coin_Name;
        this.coin_Ticker = coin_Ticker;
    }

    public Coin(int id, int user_id, double coin_Amount, double bought_Price, String coin_Ticker, String coin_Name){
        this.id = id;
        this.user_id = user_id;
        this.coin_Amount = coin_Amount;
        this.bought_Price = bought_Price;
        this.coin_Name = coin_Name;
        this.coin_Ticker = coin_Ticker;
    }


    public String getCoin_Name() {
        return coin_Name;
    }

    public void setCoin_Name(String coin_Name) {
        this.coin_Name = coin_Name;
    }

    public String getCoin_Ticker() {
        return coin_Ticker;
    }

    public void setCoin_Ticker(String coin_Ticker) {
        this.coin_Ticker = coin_Ticker;
    }

    public double getBought_Price() {
        return bought_Price;
    }

    public void setBought_Price(double bought_Price) {
        this.bought_Price = bought_Price;
    }

    public double getCoin_Amount() {
        return coin_Amount;
    }

    public void setCoin_Amount(double coin_Amount) {
        this.coin_Amount = coin_Amount;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString(){
        return coin_Ticker + ", Owned=" + coin_Amount + ", Worth=" + (coin_Amount * bought_Price);
    }
}
