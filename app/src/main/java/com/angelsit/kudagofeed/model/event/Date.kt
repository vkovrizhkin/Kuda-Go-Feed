package com.angelsit.kudagofeed.model.event

data class Date(
    val end: Long,
    val start: Long
){
    companion object {
        fun getDisplayDates(date: Date): String{
            return "7 октября"
        }
    }
}