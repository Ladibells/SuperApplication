package com.ladibells.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ladibells.datasource.local.TableConstants
import com.ladibells.datasource.local.entity.Coin

@Dao
interface CoinsDao : BaseDao<Coin> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinListInDB(coinList: List<Coin>)

    @Query("SELECT * FROM ${TableConstants.COIN_LIST}")
    fun getCoinListFromDB() : List<Coin>

    @Query("DELETE FROM ${TableConstants.COIN_LIST}")
    fun deleteAllCoinsFromDB()

}