package com.ladibells.festival.data

import androidx.lifecycle.viewModelScope
import com.ladibells.datasource.local.entity.FestivalsData
import com.ladibells.festival.domain.repository.IFestivalRepository
import com.ladibells.utilities.Resource
import com.ladibells.utilities.logging.AppLogger
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import javax.inject.Inject

class FestivalRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : IFestivalRepository {
    override suspend fun getFestivalsData(): Flow<Resource<FestivalsData?>> = flow {
        try {
            emit(Resource.Loading())

            val festivals = supabaseClient
                .postgrest["FestivalsData"]
                .select()
                .decodeList<FestivalsData>()

            AppLogger.d(message = festivals.toString())
            AppLogger.d(message = festivals.size.toString())

            festivals.getOrNull(0)?.also { festival ->
                festival.festivalDate?.also {
                    if (isTodayFestival(it)) {
                        AppLogger.d(message = "Yes today is festival")
                        emit(Resource.Success(festival))
                    } else {
                        AppLogger.d(message = "No today is not festival")
                        emit(Resource.Success(null)
                        )
                    }
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

    private fun isTodayFestival(festivalDate: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd")
        return try {
            val parsedDate = LocalDate.parse(festivalDate, formatter)
            val today = LocalDate.now()
            parsedDate.isEqual(today)
        } catch (e: DateTimeParseException) {
            return false
        }
    }
}