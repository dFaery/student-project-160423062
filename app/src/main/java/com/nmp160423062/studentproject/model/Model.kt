package com.nmp160423062.studentproject.model

import com.google.gson.annotations.SerializedName

data class Student(
    var id:String?,
    //parameter di serializedName menggunakan key yg ada di json
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var bod:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)
