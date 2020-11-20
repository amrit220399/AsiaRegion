package com.apsinnovations.asiaregion.converters;

import androidx.room.TypeConverter;

import com.apsinnovations.asiaregion.models.CountryLanguagesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CountriesConverter {

    @TypeConverter
    public static List<String> fromBordersString(String value){
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromBordersList(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<CountryLanguagesModel> fromLanguagesString(String value){
        Type listType = new TypeToken<ArrayList<CountryLanguagesModel>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromLanguagesList(List<CountryLanguagesModel> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }



}
