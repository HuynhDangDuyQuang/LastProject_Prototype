
package com.duyquang.lastproject.NutritionixObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fields {

    @SerializedName("item_id")
    @Expose
    private String item_id;
    @SerializedName("item_name")
    @Expose
    private String item_name;
    @SerializedName("brand_name")
    @Expose
    private String brand_name;
    @SerializedName("nf_calories")
    @Expose
    private Float nf_calories;
    @SerializedName("nf_total_fat")
    @Expose
    private Float nf_total_fat;
    @SerializedName("nf_cholesterol")
    @Expose
    private Float nf_cholesterol;
    @SerializedName("nf_total_carbohydrate")
    @Expose
    private Float nf_total_carbohydrate;
    @SerializedName("nf_protein")
    @Expose
    private Float nf_protein;
    @SerializedName("nf_serving_size_qty")
    @Expose
    private Integer nf_serving_size_qty;
    @SerializedName("nf_serving_size_unit")
    @Expose
    private String nf_serving_size_unit;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public Float getNf_calories() {
        return nf_calories;
    }

    public void setNf_calories(Float nf_calories) {
        this.nf_calories = nf_calories;
    }

    public Float getNf_total_fat() {
        return nf_total_fat;
    }

    public void setNf_total_fat(Float nf_total_fat) {
        this.nf_total_fat = nf_total_fat;
    }

    public Float getNf_cholesterol() {
        return nf_cholesterol;
    }

    public void setNf_cholesterol(Float nf_cholesterol) {
        this.nf_cholesterol = nf_cholesterol;
    }

    public Float getNf_total_carbohydrate() {
        return nf_total_carbohydrate;
    }

    public void setNf_total_carbohydrate(Float nf_total_carbohydrate) {
        this.nf_total_carbohydrate = nf_total_carbohydrate;
    }

    public Float getNf_protein() {
        return nf_protein;
    }

    public void setNf_protein(Float nf_protein) {
        this.nf_protein = nf_protein;
    }

    public Integer getNf_serving_size_qty() {
        return nf_serving_size_qty;
    }

    public void setNf_serving_size_qty(Integer nf_serving_size_qty) {
        this.nf_serving_size_qty = nf_serving_size_qty;
    }

    public String getNf_serving_size_unit() {
        return nf_serving_size_unit;
    }

    public void setNf_serving_size_unit(String nf_serving_size_unit) {
        this.nf_serving_size_unit = nf_serving_size_unit;
    }

}