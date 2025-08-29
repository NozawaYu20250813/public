package com.book.manager.service

import com.book.manager.model.AuthorForm
import com.book.manager.model.BookEntity
import com.book.manager.model.BookForm
import com.book.manager.model.BookListResponse
import com.book.manager.model.BookResponse
import com.book.manager.model.GetBooksForm
import com.book.manager.model.RegisterForm
import com.book.manager.tables.Author.AUTHOR
import com.book.manager.tables.Book.BOOK
import com.book.manager.tables.records.BookRecord
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.SelectConditionStep
import org.jooq.impl.DSL
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime.now
import java.util.Date


@Service
class BookManagerService(private val dsl: DSLContext) {

    fun loadRegisterForm(registerForm: RegisterForm){

        var bookFormList = registerForm.bookFormList
        var authorFormList =  registerForm.authorFormList

        for( bookForm in bookFormList){
            if((dsl.selectFrom(BOOK).where(BOOK.ID.eq(bookForm.id))) != null){
                updateBook(bookForm)
            }else{
                createBook(bookForm)
            }
        }

        for( authorForm in authorFormList){
            if((dsl.selectFrom(AUTHOR).where(AUTHOR.ID.eq(authorForm.id))) != null){
                updateAuthor(authorForm)
            }else{
                createAuthor(authorForm)
            }
        }
    }

    fun getBooks(getBooksForm : GetBooksForm): BookListResponse?{

        var bookList: List<BookResponse>?
        var response: BookListResponse? = null
        var book :BookResponse?

        //Bookテーブルから著者IDが一致するものを取得する
        val pattern = DSL.concat("%", getBooksForm.authorId, "%")
        var dbBooklist: SelectConditionStep<BookRecord?> =
            dsl.selectFrom(BOOK)
            .where(BOOK.AUTHOR_IDS.like(pattern))


        //レスポンス形式に変換する
//        if(dbBooklist != null){
//            for( dbBook in dbBooklist){
//                book.title(dbBook.title)
//            }
//
//        }

        return  response

    }

    @Transactional
    fun createBook(bookForm: BookForm){
        dsl.insertInto(BOOK).set(BOOK.ID,bookForm.id)
            .set(BOOK.TITLE,bookForm.title)
            .set(BOOK.PRICE,bookForm.price)
            .set(BOOK.AUTHOR_IDS,bookForm.authorIds)
            .set(BOOK.IS_PUBLISHED,bookForm.isPublished)
            .set(BOOK.CREATE_TIME,now())
            .set(BOOK.UPDATE_TIME,now())
    }

    @Transactional
    fun updateBook(bookForm: BookForm){
        dsl.update(BOOK)
            .set(BOOK.TITLE,bookForm.title)

            .set(BOOK.AUTHOR_IDS,bookForm.authorIds)

            .set(BOOK.UPDATE_TIME,now())
            .where(BOOK.ID.eq(bookForm.id))

        if(bookForm.price > 0){
            dsl.update(BOOK)
                .set(BOOK.PRICE,bookForm.price)
                .set(BOOK.UPDATE_TIME,now())
                .where(BOOK.ID.eq(bookForm.id))
        }

        if(!bookForm.isPublished){
            dsl.update(BOOK)
                .set(BOOK.IS_PUBLISHED,bookForm.isPublished)
                .set(BOOK.UPDATE_TIME,now())
                .where(BOOK.ID.eq(bookForm.id))
        }

    }

    @Transactional
    fun createAuthor(authorForm: AuthorForm){

        val date1 = Date()
        if( authorForm.birthDate.before(date1)){
            dsl.insertInto(AUTHOR)
                .set(AUTHOR.ID,authorForm.id)
                .set(AUTHOR.NAME,authorForm.name)
                .set(AUTHOR.BIRTH_DATE as Record?, authorForm.birthDate as Record?)
                .set(AUTHOR.BOOK_IDS, authorForm.bookIds)
                .set(AUTHOR.CREATE_TIME,now())
                .set(AUTHOR.UPDATE_TIME,now())
        }
    }


    @Transactional
    fun updateAuthor(authorForm: AuthorForm){
        val date1 = Date()
        dsl.update(AUTHOR)
            .set(AUTHOR.NAME,authorForm.name)
            .set(AUTHOR.BOOK_IDS, authorForm.bookIds)
            .set(AUTHOR.UPDATE_TIME,now())
            .where(AUTHOR.ID.eq(authorForm.id))

        if( authorForm.birthDate.before(date1)){
            dsl.update(AUTHOR)
            //.set(AUTHOR.BIRTH_DATE as Record?, authorForm.birthDate as Record?)
            .set(AUTHOR.UPDATE_TIME,now())
            .where(AUTHOR.ID.eq(authorForm.id))
        }
    }
}