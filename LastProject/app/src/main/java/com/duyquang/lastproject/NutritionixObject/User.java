package com.duyquang.lastproject.NutritionixObject;


import io.realm.RealmList;
import io.realm.RealmObject;

public class User extends RealmObject {
    private String name;
    private boolean isFemale;
    private int age, height, weight, goal;
    private int active;
//    private boolean isCreated=false;
    private RealmList<UserTrackingData> userTrackingData;

    public User(){}

//    public boolean isCreated() {
//        return isCreated;
//    }
//
//    public void setCreated(boolean created) {
//        isCreated = created;
//    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale(boolean female) {
        isFemale = female;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public int getHeight(){
        return height;
    }
    public void setHeight(int height){
        this.height = height;
    }

    public int getWeight(){
        return weight;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getActive() {return active;}
    public void setActive(int active) {
        this.active=active;
    }


    public int getGoal(){
        return goal;
    }
    public void setGoal(int goal){
        this.goal = goal;
    }

    public RealmList<UserTrackingData> getUserTrackingData() {
        return userTrackingData;
    }

    public void setUserTrackingData(RealmList<UserTrackingData> userTrackingData) {
        this.userTrackingData = userTrackingData;
    }
}
