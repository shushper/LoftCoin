package com.loftschool.loftcoin;

import com.google.gson.annotations.SerializedName;

public class Coin {
    public String id;
    public String name;
    public String symbol;
    @SerializedName("price_usd")
    public double priceUsd;
    @SerializedName("percent_change_24h")
    public double percentChange;
}
