package com.book.manager.model

import java.util.Date


data class AuthorForm(
    var id: String,
    var name: String,
    var birthDate: Date,
    var bookIdCount: Long,
    var bookIds: String
)
