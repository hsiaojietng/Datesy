package com.example.datesy

data class DateIdea(
    val title: String,
    val location: String,
    val description: String,
    val price: Int,
    var isChecked: Boolean = false
) {

}