package com.apsinnovations.asiaregion.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.apsinnovations.asiaregion.models.AsianCountriesModel;

import java.util.List;

@Dao
public interface AsianCountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAsianCountries(List<AsianCountriesModel> asianCountries);

    @Query("Select * from asian_countries")
    LiveData<List<AsianCountriesModel>> getAsianCountries();

    @Query("Delete from asian_countries")
    void deleteAll();
}
