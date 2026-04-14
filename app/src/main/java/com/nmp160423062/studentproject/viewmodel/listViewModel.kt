package com.nmp160423062.studentproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nmp160423062.studentproject.model.Student

//volley butuh parameter this/context. namun karena sekarang ini ada di view model, maka harus ubah inheritance viewmodel ke AndroidViewModel
class ListViewModel(application: Application): AndroidViewModel(application) {
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    //TAG variable is useful later on volley request cancellation and delete everything inside refresh method
    val TAG = "volleytag"

    private var queue: RequestQueue ?= null

    fun refresh() {
        loadingLD.value = true 			// progress bar start muncul
        studentLoadErrorLD.value = false  	// tidak ada error

//        studentsLD.value = arrayListOf(
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100"
//                    + ".jpg/cc0000/ffffff"),
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100" +
//                    ".jpg/5fa2dd/ffffff"),
//            Student("11204","Dinny","1994/10/07","6827808747",
//                "http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
//        )

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://www.jsonkeeper.com/b/LLMW"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                //nama variablenyay anonymus object
                val sType = object: TypeToken<List<Student>>(){}.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentsLD.value = result as ArrayList
                Log.d("showvoley", it)

                loadingLD.value = false           // Loading selesai
                studentLoadErrorLD.value = false  // Pastikan tidak ada error
            },
            {
                loadingLD.value = false          // Loading selesai (berhenti berputar)
                studentLoadErrorLD.value = true  // Tandai bahwa terjadi error
                Log.d("volley_status", it.message.toString())
            }
        )

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        //Cancel all request that identified by TAG
        queue?.cancelAll(TAG)
    }

}
