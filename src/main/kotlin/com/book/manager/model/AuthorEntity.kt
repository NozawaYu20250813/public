package com.book.manager.model

import java.sql.Date
import java.time.LocalDateTime


data class AuthorEntity(
    var id: Long,
    var name: String,
    var birthDate: Date,
    var bookIdCount: Long,
    var bookIds: String,
    var updateTime: LocalDateTime,
    var createTime: LocalDateTime
)
