package com.android.accounts_keeper_android;

/**
 * Accounts metadata
 */
public class Accounts {
    // 食物
    public double food;
    // 水果
    public double fruits;
    // 生活用品
    public double necessaries;
    // 学习用品
    public double schoolThings;
    // 备忘
    public String note;
    // 日期 format: yyyy-MM-dd
    public String date;

    public Accounts(String date) throws IllegalArgumentException {
        food = 0;
        fruits = 0;
        necessaries = 0;
        schoolThings = 0;
        note = "";

        if (!date.matches("\\d{4}-\\d{2}-\\d{2}"))
            throw new IllegalArgumentException("Wrong date");
        this.date = date;
    }

    public void setFruits(double fruits) {
        this.fruits = fruits;
    }

    public void setNecessaries(double necessaries) {
        this.necessaries = necessaries;
    }

    public void setSchoolThings(double schoolThings) {
        this.schoolThings = schoolThings;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getFruits() {
        return fruits;
    }

    public double getNecessaries() {
        return necessaries;
    }

    public double getSchoolThings() {
        return schoolThings;
    }

    public String getNote() {
        return note;
    }
}
