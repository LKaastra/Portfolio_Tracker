package com.lucak.classes;

/**
 * Created by lkaastra6885 on 11/27/2017.
 */

public class Coin {
    private String id;
    private int user_id;
    private String coin_Name;
    private double coin_Amount;
    private double priceBought;
    private double priceCurrent;
    private String symbol;
    private String oneDayChange;
    private String sevenDayChange;

    public Coin() {
        this.id = "";
        this.user_id = 0;
        this.coin_Name = "";
        this.coin_Amount = 0;
        this.priceBought = 0;
        this.priceCurrent = 0;
        this.symbol = "";
        this.oneDayChange = "";
        this.sevenDayChange = "";

    }

    public Coin(String coin_Name, double coin_Amount, double priceBought,
                double priceCurrent, String symbol, String oneDayChange, String sevenDayChange) {
        this.id = "";
        this.user_id = 0;
        this.coin_Name = coin_Name;
        this.coin_Amount = coin_Amount;
        this.priceBought = priceBought;
        this.priceCurrent = priceCurrent;
        this.symbol = symbol;
        this.oneDayChange = oneDayChange;
        this.sevenDayChange = sevenDayChange;
    }

    public Coin(String id, int user_id, String coin_Name, double coin_Amount, double priceBought,
                double priceCurrent, String symbol, String oneDayChange, String sevenDayChange) {
        this.id = id;
        this.user_id = user_id;
        this.coin_Name = coin_Name;
        this.coin_Amount = coin_Amount;
        this.priceBought = priceBought;
        this.priceCurrent = priceCurrent;
        this.symbol = symbol;
        this.oneDayChange = oneDayChange;
        this.sevenDayChange = sevenDayChange;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCoin_Name() {
        return coin_Name;
    }

    public void setCoin_Name(String coin_Name) {
        this.coin_Name = coin_Name;
    }

    public double getCoin_Amount() {
        return coin_Amount;
    }

    public void setCoin_Amount(double coin_Amount) {
        this.coin_Amount = coin_Amount;
    }

    public double getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(double priceBought) {
        this.priceBought = priceBought;
    }

    public double getPriceCurrent() {
        return priceCurrent;
    }

    public void setPriceCurrent(double priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOneDayChange() {
        return oneDayChange;
    }

    public void setOneDayChange(String oneDayChange) {
        this.oneDayChange = oneDayChange;
    }

    public String getSevenDayChange() {
        return sevenDayChange;
    }

    public void setSevenDayChange(String sevenDayChange) {
        this.sevenDayChange = sevenDayChange;
    }
}
