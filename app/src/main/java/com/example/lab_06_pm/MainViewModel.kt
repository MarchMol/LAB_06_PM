package com.example.lab_06_pm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab_06_pm.model.Post
import com.example.lab_06_pm.model.post2pck.Post2
import com.example.lab_06_pm.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponseHref: MutableLiveData<Response<Post2>> = MutableLiveData()
    fun getPost(){
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
    fun getPost2(uaLocator:String){
        viewModelScope.launch {
            val response = repository.getHref(uaLocator)
            myResponseHref.value = response
        }
    }

}