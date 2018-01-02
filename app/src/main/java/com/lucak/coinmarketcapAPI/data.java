package com.lucak.coinmarketcapAPI;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.JsonReader;

import com.lucak.Database.Database;
import com.lucak.classes.Coin;

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


    public data(Context context) {
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ArrayList<Coin> coins = APICallAll();
        if (coins != null) {
            Database data = new Database(context);
            data.RefreshAllCoins(coins);
        }
        return null;

    }

    public void APICallSingle(String name) {
        Coin coin = new Coin();
        String urlBuild = "https://api.coinmarketcap.com/v1/ticker/";
        if (!name.equals("")) {
            urlBuild += name + "?convert=CAD";
        try {
            URL coinMarketCapRoot = new URL(urlBuild);
            HttpsURLConnection myConnection = (HttpsURLConnection) coinMarketCapRoot.openConnection();
            if (myConnection.getResponseCode() == 200) {
                InputStreamReader infoReader = new InputStreamReader(myConnection.getInputStream(), "UTF-8");

                JsonReader jsonReader = new JsonReader(infoReader);

                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String key = jsonReader.nextName();
                    switch (key) {
                        case "price_cad":
                            coin.setPriceCurrent(Double.parseDouble(jsonReader.nextString()));

                            break;
                        case "percent_change_24h":
                            coin.setOneDayChange(jsonReader.nextString());

                            break;
                        case "percent_change_7d":
                            coin.setSevenDayChange(jsonReader.nextString());

                            break;
                        default:

                    }

                }
                jsonReader.close();
                myConnection.disconnect();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    public ArrayList<Coin> APICallAll() {
        ArrayList<Coin> coins = new ArrayList<Coin>();
        Coin coin = new Coin();
        String urlBuild = "https://api.coinmarketcap.com/v1/ticker/?convert=CAD";
            try {
                URL coinMarketCapRoot = new URL(urlBuild);
                HttpsURLConnection myConnection = (HttpsURLConnection) coinMarketCapRoot.openConnection();
                if (myConnection.getResponseCode() == 200) {
                    InputStreamReader infoReader = new InputStreamReader(myConnection.getInputStream(), "UTF-8");

                    JsonReader jsonReader = new JsonReader(infoReader);

                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        jsonReader.beginObject();
                        while(jsonReader.hasNext()) {
                            String key = jsonReader.nextName();
                            switch (key) {
                                case "price_cad":
                                    coin.setPriceCurrent(Double.parseDouble(jsonReader.nextString()));

                                    break;
                                case "percent_change_24h":
                                    coin.setOneDayChange(jsonReader.nextString());

                                    break;
                                case "percent_change_7d":
                                    coin.setSevenDayChange(jsonReader.nextString());

                                    break;
                                default:
                                    jsonReader.nextString();

                            }
                        }

                    }
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

