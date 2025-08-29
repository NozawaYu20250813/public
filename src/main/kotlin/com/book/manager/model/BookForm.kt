package com.book.manager.model

import java.math.BigDecimal
import java.time.ZonedDateTime


data class BookForm(
    var id: String,
    var title: String,
    var price: BigDecimal,
    var isPublished: Boolean,
    var authorIds: String
)
