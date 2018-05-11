package com.duyquang.lastproject.NutritionixObject;

import io.realm.RealmList;
import io.realm.RealmObject;

public class UserTrackingData extends RealmObject {
    private int year;
    private int month;
    private int day;
    private RealmList<ItemDetail> breakfast;
    private RealmList<ItemDetail> lunch;
    private RealmList<ItemDetail> dinner;
    private RealmList<ItemDetail> snack;

    public UserTrackingData(){}

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public RealmList<ItemDetail> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(RealmList<ItemDetail> breakfast) {
        this.breakfast = breakfast;
    }

    public RealmList<ItemDetail> getDinner() {
        return dinner;
    }

    public void setDinner(RealmList<ItemDetail> dinner) {
        this.dinner = dinner;
    }

    public RealmList<ItemDetail> getLunch() {
        return lunch;
    }

    public void setLunch(RealmList<ItemDetail> lunch) {
        this.lunch = lunch;
    }

    public RealmList<ItemDetail> getSnack() {
        return snack;
    }

    public void setSnack(RealmList<ItemDetail> snack) {
        this.snack = snack;
    }
}
