package com.hossain_ehs.speertechnologiesandroidassessmen.data.remote.dto

data class SearchDto (
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)