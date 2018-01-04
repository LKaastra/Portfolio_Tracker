package com.lucak.coinmarketcapAPI;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.widget.Toast;

import com.lucak.Database.Database;
import com.lucak.Database.db;
import com.lucak.classes.Coin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Lucak on 1/3/2018.
 */

public class MyBacgroundService extends Service {
    private Timer timer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        startTimer();
    }

    private void startTimer(){
        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                Database data = new Database(MyBacgroundService.this);
                ArrayList<Coin> coins = APICallAll();
                if (coins != null) {
                    data.RefreshAllCoins(coins);
                }
                double totalNumber = 0;
                double totalPercent = 0;
                ArrayList<Coin> infoCoins = data.GetAllCoins();
                for (Coin coin : infoCoins) {
                    Coin newCoin = APICallSingle(coin.getId());
                    if (newCoin != null) {
                        coin.setPriceCurrent(newCoin.getPriceCurrent());
                        coin.setSevenDayChange(newCoin.getSevenDayChange());
                        coin.setOneDayChange(newCoin.getOneDayChange());
                        data.UpdateCoin(coin);
                        totalNumber += coin.getPriceCurrent();
                        totalPercent += Double.parseDouble(coin.getOneDayChange());
                    }
                }
                totalPercent = totalPercent / infoCoins.size();
                db.myDB.TotalPercent = totalPercent;
                db.myDB.TotalNumber = totalNumber;

                Log.d("Service", "Completed");



            }
        };

        timer = new Timer(true);
        int delay = 1000 * 5;
        int interval = 1000 * 10;
        timer.schedule(task, delay, interval);
    }

    private void stopTimer(){
        if (timer != null){
            timer.cancel();
        }
    }

    public Coin APICallSingle(String name) {
        Coin coin = new Coin();
        String urlBuild = "https://api.coinmarketcap.com/v1/ticker/" + name + "?convert=CAD";
        try {
            URL coinMarketCapRoot = new URL(urlBuild);
            HttpsURLConnection myConnection = (HttpsURLConnection) coinMarketCapRoot.openConnection();
            if (myConnection.getResponseCode() == 200) {
                InputStreamReader infoReader = new InputStreamReader(myConnection.getInputStream(), "UTF-8");

                JsonReader jsonReader = new JsonReader(infoReader);

                jsonReader.beginArray();
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String key = jsonReader.nextName();
                    switch (key) {
                        case "price_cad":
                            coin.setPriceCurrent(Math.round(Double.parseDouble(jsonReader.nextString()) * 100d)/100d);

                            break;
                        case "percent_change_24h":
                            coin.setOneDayChange(jsonReader.nextString());

                            break;
                        case "percent_change_7d":
                            coin.setSevenDayChange(jsonReader.nextString());

                            break;
                        default:
                            JsonToken check = jsonReader.peek();
                            if (check != JsonToken.NULL){
                                jsonReader.nextString();
                            }
                            else{
                                jsonReader.nextNull();
                            }

                    }

                }
                jsonReader.endObject();
                jsonReader.endArray();
                jsonReader.close();
                myConnection.disconnect();
            }
            return coin;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Coin> APICallAll() {
        ArrayList<Coin> coins = new ArrayList<Coin>();

        String urlBuild = "https://api.coinmarketcap.com/v1/ticker/?convert=CAD&limit=100";
        try {
            URL coinMarketCapRoot = new URL(urlBuild);
            HttpsURLConnection myConnection = (HttpsURLConnection) coinMarketCapRoot.openConnection();
            if (myConnection.getResponseCode() == 200) {
                InputStreamReader infoReader = new InputStreamReader(myConnection.getInputStream(), "UTF-8");

                JsonReader jsonReader = new JsonReader(infoReader);

                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    jsonReader.beginObject();
                    Coin coin = new Coin();
                    while(jsonReader.hasNext()) {
                        String key = jsonReader.nextName();
                        switch (key) {
                            case "symbol":
                                coin.setSymbol(jsonReader.nextString());

                                break;
                            case "id":
                                coin.setId(jsonReader.nextString());

                                break;
                            case "name":
                                coin.setCoin_Name(jsonReader.nextString());
                                break;

                            default:
                                JsonToken check = jsonReader.peek();
                                if (check != JsonToken.NULL){
                                    jsonReader.nextString();
                                }
                                else{
                                    jsonReader.nextNull();
                                }
                        }

                    }
                    jsonReader.endObject();
                    coins.add(coin);

                }
                jsonReader.endArray();
                jsonReader.close();
                myConnection.disconnect();
            }
            return coins;


        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
