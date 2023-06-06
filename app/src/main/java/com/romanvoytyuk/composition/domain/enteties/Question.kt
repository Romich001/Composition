package com.romanvoytyuk.composition.domain.enteties

data class Question(
    val sum: Int,
    val visibleNumber: Int,
    val options: List<Int>
)
