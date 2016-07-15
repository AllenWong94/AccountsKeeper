package com.android.accounts_keeper_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter for Accounts ListView
 */
public class AccountsItemAdapter extends ArrayAdapter<AccountsItem> {
    private List<AccountsItem> data;
    private Context ctx;
    private int layout;

    public AccountsItemAdapter(Context ctx, int layout, List<AccountsItem> accounts) {
        super(ctx, layout, accounts);
        this.ctx = ctx;
        this.layout = layout;
        this.data = accounts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AccountsItem a = data.get(position);

        TableRow row = (TableRow) LayoutInflater.from(ctx).inflate(layout, null);
        ImageView icon = (ImageView)row.findViewById(R.id.item_accounts_icon);
        TextView title = (TextView)row.findViewById(R.id.item_accounts_title);
        TextView cost = (TextView)row.findViewById(R.id.item_accounts_cost);

        icon.setImageDrawable(ctx.getResources().getDrawable(a.getIcon()));
        title.setText(a.getItemName());

        if (position != data.size() - 1)
          cost.setText(String.format("%,.2f", a.getCost()));
        else
          cost.setText(a.getNote());

        return row;
    }
}
