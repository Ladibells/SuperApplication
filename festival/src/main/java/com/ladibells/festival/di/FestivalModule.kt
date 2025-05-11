package com.ladibells.festival.di

import com.ladibells.festival.data.FestivalRepositoryImpl
import com.ladibells.festival.domain.repository.IFestivalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

@Module
@InstallIn(SingletonComponent::class)
class FestivalModule {

    @Provides
    @Singleton
    fun providesSupabase() : SupabaseClient {
        val supabase = createSupabaseClient(
            supabaseUrl = "https://qmdvrcjsdnkckbkifxzb.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InFtZHZyY2pzZG5rY2tia2lmeHpiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDUwMjg2NjIsImV4cCI6MjA2MDYwNDY2Mn0.X3Zh9IFHIbeiZTIutvNE-sWRFC5hTzEIuLwtpI1U3uw"
        ){
            install(Postgrest)
        }
        return supabase
    }

    @Provides
    @Singleton
    fun providesFestivalRepository(supabaseClient: SupabaseClient) : IFestivalRepository {
        return FestivalRepositoryImpl(supabaseClient)
    }
}