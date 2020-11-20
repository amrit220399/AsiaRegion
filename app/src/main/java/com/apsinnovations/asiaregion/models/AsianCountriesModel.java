package com.apsinnovations.asiaregion.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.apsinnovations.asiaregion.converters.CountriesConverter;

import java.util.List;

@Entity(tableName = "asian_countries")
public class AsianCountriesModel {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "capital")
    private String capital;

    @ColumnInfo(name = "flag")
    private String flag;

    @ColumnInfo(name = "region")
    private String region;

    @ColumnInfo(name = "subregion")
    private String subregion;

    @ColumnInfo(name = "population")
    private int population;


    @ColumnInfo(name = "borders")
    private List<String> borders;

    @ColumnInfo(name = "languages")
    private List<CountryLanguagesModel> languages;

    public AsianCountriesModel(String name, String capital, String flag, String region, String subregion, int population, List<String> borders, List<CountryLanguagesModel> languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public int getPopulation() {
        return population;
    }

    public List<String> getBorders() {
        return borders;
    }

    public List<CountryLanguagesModel> getLanguages() {
        return languages;
    }

    @Override
    public String toString() {
        return "AsianCountriesModel{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", flag='" + flag + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population=" + population +
                ", borders=" + borders +
                ", languages=" + languages +
                '}';
    }
}
