package com.ladibells.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ladibells.datasource.local.TableConstants
import com.ladibells.datasource.local.entity.UserLocation

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserLocationInDB(userLocation: UserLocation)

    @Query("SELECT * FROM ${TableConstants.USER_LOCATION}")
    fun getUserLocationFromDB() : UserLocation?

}