package com.ladibells.festival.data.model

import com.ladibells.festival.R

data class FestivalMessage(
    val resource : Int? = null,
    val message : String? = null
)

object FestivalConstants {
    private val messages = listOf(
        FestivalMessage(
            resource = R.raw.holi,
            message = "Holi is a celebration of love, joy, and colors that unite us all"
        ),
        FestivalMessage(
            resource = R.raw.holi,
            message = "With every splash of color, Holi brings us together"
        ),
        FestivalMessage(
            resource = R.raw.holi,
            message = "Holi is the perfect time to forgive, forget and start fresh"
        ),
        FestivalMessage(
            resource = R.raw.holi,
            message = "Holi shows us that happiness has no borders, only colors"
        ),
        FestivalMessage(
            resource = R.raw.holi,
            message = "Let the colors of Holi remind us of the beauty in our differences"
        ),
    )

    fun getFestivalMessages() : List<FestivalMessage> {
        return messages
    }
}
