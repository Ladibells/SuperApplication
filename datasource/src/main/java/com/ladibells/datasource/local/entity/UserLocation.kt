package com.ladibells.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ladibells.datasource.local.TableConstants

@Entity(tableName = TableConstants.USER_LOCATION)
data class UserLocation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val cityName: String = "",
)
