package com.googleapp.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "articles"

)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
    //need tranfer article we clicked to fragment ->
    // need save argument from navigation compoment
    //need mark this class serializable bc article class
    // not primitive data type, able to pass class bw multi fragment
) : Serializable {
    override fun hashCode(): Int {
        var result = id.hashCode()
        if(url.isNullOrEmpty()){
            result = 31 * result + url.hashCode()
        }
        return result
    }
}


