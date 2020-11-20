package com.apsinnovations.asiaregion.apis;

import com.apsinnovations.asiaregion.models.AsianCountriesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("asia")
    Call<List<AsianCountriesModel>> getAsianCountries();
}
