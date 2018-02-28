package com.loftschool.loftcoin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CoinsAdapter extends RecyclerView.Adapter<CoinsAdapter.CoinViewHolder> {

    private List<Coin> coins = Collections.emptyList();

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
        notifyDataSetChanged();
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coin, parent, false);
        return new CoinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        holder.bind(coins.get(position));
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    static class CoinViewHolder extends RecyclerView.ViewHolder {

        private TextView symbol;
        private TextView name;
        private TextView price;
        private TextView percentChange;

        private Random random = new Random();

        private Context context;

        private static int[] colors = {
            0xFF9C27B0,
            0xFF673AB7,
            0xFF2196F3,
            0xFF009688,
            0xFFFFC107,
            0xFFFF9800,
        };

        public CoinViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            symbol = itemView.findViewById(R.id.symbol);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            percentChange = itemView.findViewById(R.id.percent_change);
        }

        public void bind(Coin coin) {
            symbol.setText(coin.symbol);

            Drawable background = symbol.getBackground();
            Drawable wrapped = DrawableCompat.wrap(background);
            DrawableCompat.setTint(wrapped, colors[random.nextInt(colors.length)]);

            name.setText(coin.name);

            price.setText(context.getString(R.string.price, coin.priceUsd));
            percentChange.setText(context.getString(R.string.percent_change, coin.percentChange));


            if (coin.percentChange >= 0) {
                percentChange.setTextColor(context.getResources().getColor(R.color.percentChangePositive));
            } else {
                percentChange.setTextColor(context.getResources().getColor(R.color.percentChangeNegative));

            }
        }
    }
}
