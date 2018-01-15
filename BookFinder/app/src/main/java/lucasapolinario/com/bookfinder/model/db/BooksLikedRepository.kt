package lucasapolinario.com.bookfinder.model.db

import lucasapolinario.com.bookfinder.model.dataClass.Book

interface BooksLikedRepository {

    fun new(book: Book)
    fun delete(book: Book)
    fun getBooks() : ArrayList<Book>

}
