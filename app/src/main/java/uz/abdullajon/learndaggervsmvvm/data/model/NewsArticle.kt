package uz.abdullajon.learndaggervsmvvm.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NewsArticle(
    var author: String?,
    var title: String?,
    var description: String,
    var url: String,
    var urlToImage: String,
    var publishedAt: String,
    var content: String
):Parcelable