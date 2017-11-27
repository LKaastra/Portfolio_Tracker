package com.lucak.classes;

/**
 * Created by lkaastra6885 on 11/27/2017.
 */

public class Token {
    private int id;
    private int user_Id;
    private int coin_Id;
    private double token_Amount;
    private double bought_Price;
    private String token_Ticker;
    private String token_Name;

    public Token(){
        id = 0;
        user_Id = 0;
        coin_Id = 0;
        token_Amount = 0;
        bought_Price = 0;
        token_Name = "";
        token_Ticker = "";
    }

    public Token(int user_Id, int coin_Id, double token_Amount, double bought_Price, String token_Ticker, String token_Name){
        this.id = 0;
        this.user_Id = user_Id;
        this.coin_Id = coin_Id;
        this.token_Amount = token_Amount;
        this.bought_Price = bought_Price;
        this.token_Name = token_Name;
        this.token_Ticker = token_Ticker;
    }

    public Token(int id, int user_Id, int coin_Id, double token_Amount, double bought_Price, String token_Ticker, String token_Name){
        this.id = id;
        this.user_Id = user_Id;
        this.coin_Id = coin_Id;
        this.token_Amount = token_Amount;
        this.bought_Price = bought_Price;
        this.token_Name = token_Name;
        this.token_Ticker = token_Ticker;
    }

    public String getToken_Name() {
        return token_Name;
    }

    public void setToken_Name(String token_Name) {
        this.token_Name = token_Name;
    }

    public String getToken_Ticker() {
        return token_Ticker;
    }

    public void setToken_Ticker(String token_Ticker) {
        this.token_Ticker = token_Ticker;
    }

    public double getBought_Price() {
        return bought_Price;
    }

    public void setBought_Price(double bought_Price) {
        this.bought_Price = bought_Price;
    }

    public double getToken_Amount() {
        return token_Amount;
    }

    public void setToken_Amount(double token_Amount) {
        this.token_Amount = token_Amount;
    }

    public int getCoin_Id() {
        return coin_Id;
    }

    public void setCoin_Id(int coin_Id) {
        this.coin_Id = coin_Id;
    }

    public int getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(int user_Id) {
        this.user_Id = user_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
