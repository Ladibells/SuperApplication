package com.ladibells.weather.data.repository

import android.content.Context
import com.ladibells.datasource.local.entity.CityItem
import com.ladibells.utilities.Resource
import com.ladibells.weather.R
import com.ladibells.weather.domain.repository.IAddressRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val context: Context
) : IAddressRepository {
    override suspend fun getPopularCities(): Flow<Resource<List<CityItem>>> = flow{
        try {
            emit(Resource.Loading())
            val cities = mutableListOf<CityItem>()
            cities.apply {

                add(
                    CityItem(
                        icon = R.drawable.big_ben,
                        name = context.getString(R.string.peterborough)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.clock_tower_7371470,
                        name = context.getString(R.string.london)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.clock_tower_7371541,
                        name = context.getString(R.string.newcastle_upon_tyne)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.city_2,
                        name = context.getString(R.string.cornwall)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.big_ben_3,
                        name = context.getString(R.string.birmingham)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.building_9,
                        name = context.getString(R.string.brighton)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.clock_tower_73,
                        name = context.getString(R.string.leicester)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.playground_5190533,
                        name = context.getString(R.string.kent)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.big_ben_2802480,
                        name = context.getString(R.string.lagos)
                    )
                )
                add(
                    CityItem(
                        icon = R.drawable.big_ben_4369431,
                        name = context.getString(R.string.cornwall)
                    )
                )

            }
            emit(Resource.Success(cities))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }
}