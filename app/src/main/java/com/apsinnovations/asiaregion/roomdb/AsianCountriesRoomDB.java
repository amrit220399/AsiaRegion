package com.apsinnovations.asiaregion.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.apsinnovations.asiaregion.converters.CountriesConverter;
import com.apsinnovations.asiaregion.models.AsianCountriesModel;

@Database(entities = {AsianCountriesModel.class},version = 1)
@TypeConverters({CountriesConverter.class})
public abstract class AsianCountriesRoomDB extends RoomDatabase {

    public abstract AsianCountriesDao asianCountriesDao();

    private static volatile AsianCountriesRoomDB INSTANCE;

    public static AsianCountriesRoomDB getInstance(Context context){
        if(INSTANCE == null){
            synchronized (AsianCountriesRoomDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AsianCountriesRoomDB.class,"CountriesDB")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
