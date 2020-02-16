package com.nextbest.weatherappandroid.data.model

import java.io.Serializable

data class Source(
    val crawl_rate: Int,
    val slug: String,
    val title: String,
    val url: String
): Serializable