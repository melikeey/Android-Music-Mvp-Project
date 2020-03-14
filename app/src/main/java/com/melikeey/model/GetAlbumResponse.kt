package com.melikeey.model

data class GetAlbumResponse(
    val id: String,
    var next: String,
    var total: String,
    var data: List<Data>,
    val tracks: Tracks,
    val cover_medium: String)