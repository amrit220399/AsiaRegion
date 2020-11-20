package com.apsinnovations.asiaregion.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.apsinnovations.asiaregion.models.AsianCountriesModel;
import com.apsinnovations.asiaregion.repository.CountriesRepository;

import java.util.List;

public class CountriesViewModel extends AndroidViewModel {

    private CountriesRepository countriesRepository;
    private LiveData<List<AsianCountriesModel>> countries;

    public CountriesViewModel(@NonNull Application application) {
        super(application);
        countriesRepository=new CountriesRepository(application);
        countries = countriesRepository.getAsianCountries();
    }

    public void insert(List<AsianCountriesModel> countries){
        countriesRepository.insert(countries);
    }

    public LiveData<List<AsianCountriesModel>> getAsianCountries(){
        return  countries;
    }

}
