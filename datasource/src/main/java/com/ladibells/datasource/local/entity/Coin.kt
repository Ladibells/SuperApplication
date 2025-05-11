package com.ladibells.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ladibells.datasource.local.TableConstants

@Entity(tableName = TableConstants.COIN_LIST)
data class Coin(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
