package com.apsinnovations.asiaregion.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.apsinnovations.asiaregion.models.AsianCountriesModel;
import com.apsinnovations.asiaregion.roomdb.AsianCountriesDao;
import com.apsinnovations.asiaregion.roomdb.AsianCountriesRoomDB;

import java.util.List;

public class CountriesRepository {

    private AsianCountriesRoomDB db;
    private LiveData<List<AsianCountriesModel>> countries;

    public CountriesRepository(Application application) {
        db=AsianCountriesRoomDB.getInstance(application);
        countries=db.asianCountriesDao().getAsianCountries();
    }

    public void insert(List<AsianCountriesModel> countries){
        InsertAsyncTask task=new InsertAsyncTask(db);
        task.execute(countries);
    }

    public void deleteAll(){
        DeleteAllAsyncTask task =new DeleteAllAsyncTask(db);
        task.execute();
    }

    public LiveData<List<AsianCountriesModel>> getAsianCountries(){
        return countries;
    }

    static class InsertAsyncTask extends AsyncTask<List<AsianCountriesModel>,Void,Void>{

        private AsianCountriesDao asianCountriesDao;

        public InsertAsyncTask(AsianCountriesRoomDB db){
            this.asianCountriesDao=db.asianCountriesDao();
        }

        @Override
        protected Void doInBackground(List<AsianCountriesModel>... lists) {
            this.asianCountriesDao.insertAsianCountries(lists[0]);
            return null;
        }
    }

    static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private AsianCountriesDao asianCountriesDao;

        public DeleteAllAsyncTask(AsianCountriesRoomDB db){
            this.asianCountriesDao=db.asianCountriesDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.asianCountriesDao.deleteAll();
            return null;
        }
    }
}
