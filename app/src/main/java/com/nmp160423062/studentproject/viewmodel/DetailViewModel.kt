package com.nmp160423062.studentproject.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.RequestQueue
import com.nmp160423062.studentproject.model.Student

class DetailViewModel(application: Application) {
    val studentLD = MutableLiveData<Student>()
    private val TAG = "volley_tag"
    private var queue: RequestQueue?= null

    fun fetch(){
        var url = "https://www.jsonkeeper.com/b/LLMW"
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
    }
}