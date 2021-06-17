package com.krisnachy.submissiontwo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
        var username: String? = "",
        var name: String? = "",
        var avatar: String? = "",
        var company: String? = "",
        var location: String? = "",
        var repository: Int = 0,
        var followers: Int = 0,
        var following: Int = 0
) : Parcelable
