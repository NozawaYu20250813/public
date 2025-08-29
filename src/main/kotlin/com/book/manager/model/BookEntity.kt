package com.book.manager.model

import java.math.BigDecimal
import java.time.LocalDateTime


data class BookEntity(
    var id: String,
    var title: String,
    var price: BigDecimal,
    var isPublished: Boolean,
    var authorIds: String,
    var updateTime: LocalDateTime,
    var createTime: LocalDateTime
)
