package com.lucak.coinmarketcapAPI;

import android.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.JsonToken;
import android.widget.Adapter;
import android.widget.ListView;

import com.lucak.Database.Database;
import com.lucak.Database.db;
import com.lucak.classes.Coin;
import com.lucak.classes.CustomAdapter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Lucak on 1/1/2018.
 */

public class data extends AsyncTask{
    private Context context;
    private CustomAdapter adapter;
    private ListView list;


    public data(Context context, CustomAdapter adapter, ListView list) {
        this.context = context;
        this.adapter = adapter;
        this.list = list;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Database data = new Database(context);
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
                totalNumber += coin.getPriceCurrent() * coin.getCoin_Amount();
                totalPercent += Double.parseDouble(coin.getOneDayChange());
            }
        }
        totalPercent = totalPercent / infoCoins.size();
        db.myDB.TotalPercent = totalPercent;
        db.myDB.TotalNumber = totalNumber;

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        Database data = new Database(context);
        adapter.clear();
        adapter.addAll(data.GetAllCoins());
        list.setAdapter(adapter);

        super.onPostExecute(o);
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

