package com.android.accounts_keeper_android;

/**
 * Item of Accounts which contains Icon, money, cost
 */
public class AccountsItem {
    public int icon;
    public String itemName;
    public double cost;
    public String note;

    public AccountsItem(int icon, String itemName, double cost) {
        this.icon = icon;
        this.itemName = itemName;
        this.cost = cost;
    }

    public AccountsItem(int icon, String itemName, String note) {
        this.icon = icon;
        this.itemName = itemName;
        this.note = note;
    }

    public int getIcon() {
        return icon;
    }

    public String getItemName() {
        return itemName;
    }

    public double getCost() {
        return cost;
    }

    public String getNote() {
        return note;
    }
}
