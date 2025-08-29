package com.book.manager.model

import java.math.BigDecimal
import java.time.ZonedDateTime


data class BookResponse(
    var title: String,
    var price: BigDecimal,
    var authorName: String,
    var status: String,
)
