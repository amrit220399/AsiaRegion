package com.apsinnovations.asiaregion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.apsinnovations.asiaregion.adapters.CountriesAdapter;
import com.apsinnovations.asiaregion.apis.ApiClient;
import com.apsinnovations.asiaregion.apis.ApiInterface;
import com.apsinnovations.asiaregion.models.AsianCountriesModel;
import com.apsinnovations.asiaregion.repository.CountriesRepository;
import com.apsinnovations.asiaregion.viewmodel.CountriesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private CountriesAdapter countriesAdapter;
    private CountriesRepository countriesRepository;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerCountries);
        fab=findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              countriesRepository.deleteAll();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        countriesRepository = new CountriesRepository(getApplication());

        apiInterface = ApiClient.getRetrofitClient().create(ApiInterface.class);
        CountriesViewModel countriesViewModel = ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication())
                .create(CountriesViewModel.class);

        countriesViewModel.getAsianCountries()
                .observe(this, new Observer<List<AsianCountriesModel>>() {
                    @Override
                    public void onChanged(List<AsianCountriesModel> asianCountriesModels) {
                        Log.e(TAG, "onChanged: "+ asianCountriesModels.toString() );
                        countriesAdapter = new CountriesAdapter(MainActivity.this, asianCountriesModels);
                        recyclerView.setAdapter(countriesAdapter);
                    }
                });

        getAsianCountriesFromUrl();
    }

    private void getAsianCountriesFromUrl() {
        Call<List<AsianCountriesModel>> countriesCall = apiInterface.getAsianCountries();
        countriesCall.enqueue(new Callback<List<AsianCountriesModel>>() {
            @Override
            public void onResponse(Call<List<AsianCountriesModel>> call, Response<List<AsianCountriesModel>> response) {
                if (response.isSuccessful()) {
                    countriesRepository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<AsianCountriesModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

}