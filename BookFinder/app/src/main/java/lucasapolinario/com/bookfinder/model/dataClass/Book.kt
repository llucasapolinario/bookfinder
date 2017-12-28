package lucasapolinario.com.bookfinder.model.dataClass

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import org.json.JSONObject
import org.json.JSONException
import org.json.JSONArray


data class Book(
        var openLibraryId: String,
        var author: String,
        var title: String
) : Parcelable {

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }

    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(openLibraryId)
        parcel.writeString(author)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    // Get medium sized book cover from covers API
    fun getCoverUrl(): String {
        return "http://covers.openlibrary.org/b/olid/$openLibraryId-M.jpg?default=false"
    }

    // Get large sized book cover from covers API
    fun getLargeCoverUrl(): String {
        return "http://covers.openlibrary.org/b/olid/$openLibraryId-L.jpg?default=false"
    }

    fun fromJson(jsonArray: JSONArray?): ArrayList<Book>? {
        val books = ArrayList<Book>(jsonArray!!.length())
        // Process each result in json array, decode and convert to business
        // object
        for (i in 0 until jsonArray.length()) {
            var bookJson: JSONObject?
            try {
                bookJson = jsonArray.getJSONObject(i)
            } catch (e: Exception) {
                e.printStackTrace()
                continue
            }

            val book = fromJson(bookJson)
            if (book != null) {
                books.add(book)
            }
        }
        return books
    }

    private fun fromJson(jsonObject: JSONObject): Book? {

        var openLibraryId = ""
        val title: String
        val author: String

        try {
            if (jsonObject.has("cover_edition_key"))
                openLibraryId = jsonObject.getString("cover_edition_key")
            else if (jsonObject.has("edition_key"))
                openLibraryId = jsonObject.getJSONArray("edition_key").getString(0)
            title = if (jsonObject.has("title_suggest")) jsonObject.getString("title_suggest") else ""
            author = getAuthor(jsonObject)

        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }

        return Book(openLibraryId, author, title)
    }

    private fun getAuthor(jsonObject: JSONObject): String {
        return try {
            val authors = jsonObject.getJSONArray("author_name")
            val numAuthors = authors.length()
            val authorStrings = arrayOfNulls<String>(numAuthors)
            for (i in 0 until numAuthors)
                authorStrings[i] = authors.getString(i)

            TextUtils.join(", ", authorStrings)

        } catch (e: JSONException) { "" }

    }

}