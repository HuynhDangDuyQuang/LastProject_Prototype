
package com.duyquang.lastproject.NutritionixObject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ItemDetail extends RealmObject{

    @SerializedName("old_api_id")
    @Expose
    private String old_api_id;
    @SerializedName("item_id")
    @Expose
    private String item_id;
    @SerializedName("item_name")
    @Expose
    private String item_name;
    @SerializedName("leg_loc_id")
    @Expose
    private Integer leg_loc_id;
    @SerializedName("brand_id")
    @Expose
    private String brand_id;
    @SerializedName("brand_name")
    @Expose
    private String brand_name;
    @SerializedName("item_description")
    @Expose
    private String item_description;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("nf_ingredient_statement")
    @Expose
    private String nf_ingredient_statement;
    @SerializedName("nf_water_grams")
    @Expose
    private Float nf_water_grams;
    @SerializedName("nf_calories")
    @Expose
    private Float nf_calories;
    @SerializedName("nf_calories_from_fat")
    @Expose
    private Float nf_calories_from_fat;
    @SerializedName("nf_total_fat")
    @Expose
    private Float nf_total_fat;
    @SerializedName("nf_saturated_fat")
    @Expose
    private Float nf_saturated_fat;
    @SerializedName("nf_trans_fatty_acid")
    @Expose
    private Float nf_trans_fatty_acid;
    @SerializedName("nf_polyunsaturated_fat")
    @Expose
    private Float nf_polyunsaturated_fat;
    @SerializedName("nf_monounsaturated_fat")
    @Expose
    private Float nf_monounsaturated_fat;
    @SerializedName("nf_cholesterol")
    @Expose
    private Float nf_cholesterol;
    @SerializedName("nf_sodium")
    @Expose
    private Float nf_sodium;
    @SerializedName("nf_total_carbohydrate")
    @Expose
    private Float nf_total_carbohydrate;
    @SerializedName("nf_dietary_fiber")
    @Expose
    private Float nf_dietary_fiber;
    @SerializedName("nf_sugars")
    @Expose
    private Float nf_sugars;
    @SerializedName("nf_protein")
    @Expose
    private Float nf_protein;
    @SerializedName("nf_vitamin_a_dv")
    @Expose
    private Float nf_vitamin_a_dv;
    @SerializedName("nf_vitamin_c_dv")
    @Expose
    private Float nf_vitamin_c_dv;
    @SerializedName("nf_calcium_dv")
    @Expose
    private Float nf_calcium_dv;
    @SerializedName("nf_iron_dv")
    @Expose
    private Float nf_iron_dv;
    @SerializedName("nf_refuse_pct")
    @Expose
    private String nf_refuse_pct;
    @SerializedName("nf_servings_per_container")
    @Expose
    private String nf_servings_per_container;
    @SerializedName("nf_serving_size_qty")
    @Expose
    private Float nf_serving_size_qty;
    @SerializedName("nf_serving_size_unit")
    @Expose
    private String nf_serving_size_unit;
    @SerializedName("nf_serving_weight_grams")
    @Expose
    private Float nf_serving_weight_grams;
    @SerializedName("allergen_contains_milk")
    @Expose
    private String allergen_contains_milk;
    @SerializedName("allergen_contains_eggs")
    @Expose
    private String allergen_contains_eggs;
    @SerializedName("allergen_contains_fish")
    @Expose
    private String allergen_contains_fish;
    @SerializedName("allergen_contains_shellfish")
    @Expose
    private String allergen_contains_shellfish;
    @SerializedName("allergen_contains_tree_nuts")
    @Expose
    private String allergen_contains_tree_nuts;
    @SerializedName("allergen_contains_peanuts")
    @Expose
    private String allergen_contains_peanuts;
    @SerializedName("allergen_contains_wheat")
    @Expose
    private String allergen_contains_wheat;
    @SerializedName("allergen_contains_soybeans")
    @Expose
    private String allergen_contains_soybeans;
    @SerializedName("allergen_contains_gluten")
    @Expose
    private String allergen_contains_gluten;

    public ItemDetail(){}

    public String getOld_api_id() {
        return old_api_id;
    }

    public void setOld_api_id(String old_api_id) {
        this.old_api_id = old_api_id;
    }

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

    public Integer getLeg_loc_id() {
        return leg_loc_id;
    }

    public void setLeg_loc_id(Integer leg_loc_id) {
        this.leg_loc_id = leg_loc_id;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getNf_ingredient_statement() {
        return nf_ingredient_statement;
    }

    public void setNf_ingredient_statement(String nf_ingredient_statement) {
        this.nf_ingredient_statement = nf_ingredient_statement;
    }

    public Float getNf_water_grams() {
        return nf_water_grams;
    }

    public void setNf_water_grams(Float nf_water_grams) {
        this.nf_water_grams = nf_water_grams;
    }

    public Float getNf_calories() {
        return nf_calories;
    }

    public void setNf_calories(Float nf_calories) {
        this.nf_calories = nf_calories;
    }

    public Float getNf_calories_from_fat() {
        return nf_calories_from_fat;
    }

    public void setNf_calories_from_fat(Float nf_calories_from_fat) {
        this.nf_calories_from_fat = nf_calories_from_fat;
    }

    public Float getNf_total_fat() {
        return nf_total_fat;
    }

    public void setNf_total_fat(Float nf_total_fat) {
        this.nf_total_fat = nf_total_fat;
    }

    public Float getNf_saturated_fat() {
        return nf_saturated_fat;
    }

    public void setNf_saturated_fat(Float nf_saturated_fat) {
        this.nf_saturated_fat = nf_saturated_fat;
    }

    public Float getNf_trans_fatty_acid() {
        return nf_trans_fatty_acid;
    }

    public void setNf_trans_fatty_acid(Float nf_trans_fatty_acid) {
        this.nf_trans_fatty_acid = nf_trans_fatty_acid;
    }

    public Float getNf_polyunsaturated_fat() {
        return nf_polyunsaturated_fat;
    }

    public void setNf_polyunsaturated_fat(Float nf_polyunsaturated_fat) {
        this.nf_polyunsaturated_fat = nf_polyunsaturated_fat;
    }

    public Float getNf_monounsaturated_fat() {
        return nf_monounsaturated_fat;
    }

    public void setNf_monounsaturated_fat(Float nf_monounsaturated_fat) {
        this.nf_monounsaturated_fat = nf_monounsaturated_fat;
    }

    public Float getNf_cholesterol() {
        return nf_cholesterol;
    }

    public void setNf_cholesterol(Float nf_cholesterol) {
        this.nf_cholesterol = nf_cholesterol;
    }

    public Float getNf_sodium() {
        return nf_sodium;
    }

    public void setNf_sodium(Float nf_sodium) {
        this.nf_sodium = nf_sodium;
    }

    public Float getNf_total_carbohydrate() {
        return nf_total_carbohydrate;
    }

    public void setNf_total_carbohydrate(Float nf_total_carbohydrate) {
        this.nf_total_carbohydrate = nf_total_carbohydrate;
    }

    public Float getNf_dietary_fiber() {
        return nf_dietary_fiber;
    }

    public void setNf_dietary_fiber(Float nf_dietary_fiber) {
        this.nf_dietary_fiber = nf_dietary_fiber;
    }

    public Float getNf_sugars() {
        return nf_sugars;
    }

    public void setNf_sugars(Float nf_sugars) {
        this.nf_sugars = nf_sugars;
    }

    public Float getNf_protein() {
        return nf_protein;
    }

    public void setNf_protein(Float nf_protein) {
        this.nf_protein = nf_protein;
    }

    public Float getNf_vitamin_a_dv() {
        return nf_vitamin_a_dv;
    }

    public void setNf_vitamin_a_dv(Float nf_vitamin_a_dv) {
        this.nf_vitamin_a_dv = nf_vitamin_a_dv;
    }

    public Float getNf_vitamin_c_dv() {
        return nf_vitamin_c_dv;
    }

    public void setNf_vitamin_c_dv(Float nf_vitamin_c_dv) {
        this.nf_vitamin_c_dv = nf_vitamin_c_dv;
    }

    public Float getNf_calcium_dv() {
        return nf_calcium_dv;
    }

    public void setNf_calcium_dv(Float nf_calcium_dv) {
        this.nf_calcium_dv = nf_calcium_dv;
    }

    public Float getNf_iron_dv() {
        return nf_iron_dv;
    }

    public void setNf_iron_dv(Float nf_iron_dv) {
        this.nf_iron_dv = nf_iron_dv;
    }

    public String getNf_refuse_pct() {
        return nf_refuse_pct;
    }

    public void setNf_refuse_pct(String nf_refuse_pct) {
        this.nf_refuse_pct = nf_refuse_pct;
    }

    public String getNf_servings_per_container() {
        return nf_servings_per_container;
    }

    public void setNf_servings_per_container(String nf_servings_per_container) {
        this.nf_servings_per_container = nf_servings_per_container;
    }

    public Float getNf_serving_size_qty() {
        return nf_serving_size_qty;
    }

    public void setNf_serving_size_qty(Float nf_serving_size_qty) {
        this.nf_serving_size_qty = nf_serving_size_qty;
    }

    public String getNf_serving_size_unit() {
        return nf_serving_size_unit;
    }

    public void setNf_serving_size_unit(String nf_serving_size_unit) {
        this.nf_serving_size_unit = nf_serving_size_unit;
    }

    public Float getNf_serving_weight_grams() {
        return nf_serving_weight_grams;
    }

    public void setNf_serving_weight_grams(Float nf_serving_weight_grams) {
        this.nf_serving_weight_grams = nf_serving_weight_grams;
    }

    public String getAllergen_contains_milk() {
        return allergen_contains_milk;
    }

    public void setAllergen_contains_milk(String allergen_contains_milk) {
        this.allergen_contains_milk = allergen_contains_milk;
    }

    public String getAllergen_contains_eggs() {
        return allergen_contains_eggs;
    }

    public void setAllergen_contains_eggs(String allergen_contains_eggs) {
        this.allergen_contains_eggs = allergen_contains_eggs;
    }

    public String getAllergen_contains_fish() {
        return allergen_contains_fish;
    }

    public void setAllergen_contains_fish(String allergen_contains_fish) {
        this.allergen_contains_fish = allergen_contains_fish;
    }

    public String getAllergen_contains_shellfish() {
        return allergen_contains_shellfish;
    }

    public void setAllergen_contains_shellfish(String allergen_contains_shellfish) {
        this.allergen_contains_shellfish = allergen_contains_shellfish;
    }

    public String getAllergen_contains_tree_nuts() {
        return allergen_contains_tree_nuts;
    }

    public void setAllergen_contains_tree_nuts(String allergen_contains_tree_nuts) {
        this.allergen_contains_tree_nuts = allergen_contains_tree_nuts;
    }

    public String getAllergen_contains_peanuts() {
        return allergen_contains_peanuts;
    }

    public void setAllergen_contains_peanuts(String allergen_contains_peanuts) {
        this.allergen_contains_peanuts = allergen_contains_peanuts;
    }

    public String getAllergen_contains_wheat() {
        return allergen_contains_wheat;
    }

    public void setAllergen_contains_wheat(String allergen_contains_wheat) {
        this.allergen_contains_wheat = allergen_contains_wheat;
    }

    public String getAllergen_contains_soybeans() {
        return allergen_contains_soybeans;
    }

    public void setAllergen_contains_soybeans(String allergen_contains_soybeans) {
        this.allergen_contains_soybeans = allergen_contains_soybeans;
    }

    public String getAllergen_contains_gluten() {
        return allergen_contains_gluten;
    }

    public void setAllergen_contains_gluten(String allergen_contains_gluten) {
        this.allergen_contains_gluten = allergen_contains_gluten;
    }

}
