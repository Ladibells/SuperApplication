package com.ladibells.datasource.local

import android.content.Context
import com.ladibells.datasource.local.entity.Coin
import com.ladibells.datasource.local.entity.UserLocation
import dagger.hilt.android.qualifiers.ApplicationContext

class LocalDataSourceImpl(
    @ApplicationContext private val context: Context
) : LocalDataSource {

    private val appDB : AppDB = AppDB.getDatabase(context)

    //wealth Module
    override fun getCoinListFromDB(): List<Coin> {
        return appDB.getCoinsDao().getCoinListFromDB()
    }

    override fun insertCoinListInDB(coinList: List<Coin>) {
        appDB.getCoinsDao().insertCoinListInDB(coinList)
    }

    override fun deleteAllCoinsFromDB() {
        appDB.getCoinsDao().deleteAllCoinsFromDB()
    }

    //weather Module
    override fun insertUserLocationInDB(cityName: String) {
        appDB.getLocationDao().insertUserLocationInDB(userLocation = UserLocation(cityName = cityName))
    }

    override fun getUserLocationFromDB(): String {
        return appDB.getLocationDao().getUserLocationFromDB()?.cityName ?: ""
    }
}