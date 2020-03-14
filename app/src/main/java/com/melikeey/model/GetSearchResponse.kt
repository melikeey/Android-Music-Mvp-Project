package com.melikeey.model

data class GetSearchResponse (
    val id: String,
    var next: String,
    var total: String,
    var data: List<Data>)
