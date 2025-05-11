package com.ladibells.weather.di

import android.content.Context
import com.ladibells.utilities.constants.AppConstants
import com.ladibells.weather.data.remote.WeatherApi
import com.ladibells.weather.data.repository.AddressRepositoryImpl
import com.ladibells.weather.data.repository.WeatherRepositoryImpl
import com.ladibells.weather.domain.repository.IAddressRepository
import com.ladibells.weather.domain.repository.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherModule {

    @Provides
    @Singleton
    fun provideAddressRepository(@ApplicationContext context: Context): IAddressRepository {
        return AddressRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun providesWeatherApi() : WeatherApi {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(httpLoggingInterceptor)

        val interceptor = Interceptor { chain ->
            val request = chain.request()
                .newBuilder()
//                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
        httpClient.addInterceptor(interceptor)

        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL_WEATHER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesWeatherRepository(api: WeatherApi) : IWeatherRepository {
        return WeatherRepositoryImpl(api)

    }
}