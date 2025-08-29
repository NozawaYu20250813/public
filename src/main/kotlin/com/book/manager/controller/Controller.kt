package com.book.manager.controller

import com.book.manager.model.BookListResponse
import com.book.manager.model.GetBooksForm
import com.book.manager.model.RegisterForm
import com.book.manager.service.BookManagerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book/manager")
class Controller (private val bookManagerService: BookManagerService){

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterForm) {
        bookManagerService.loadRegisterForm(request)
    }

//    @PostMapping("/getBooks")
//    fun getBooks(@RequestBody request: GetBooksForm) : BookListResponse {
//
//        var response :BookListResponse
//
//        return  response
//
//    }
}