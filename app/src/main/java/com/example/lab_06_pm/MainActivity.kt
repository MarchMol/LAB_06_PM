package com.example.lab_06_pm

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lab_06_pm.api.RetrofitInstance
import com.example.lab_06_pm.api.SimpleAPI
import com.example.lab_06_pm.model.Post
import com.example.lab_06_pm.model.Ua_item
import com.example.lab_06_pm.repository.Repository
import com.example.lab_06_pm.ui.theme.LAB_06_PMTheme
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)



        setContent {
            LAB_06_PMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(viewModel, this)
                }
            }
        }
    }
}


@Composable
fun Greeting(viewModel: MainViewModel, activity: MainActivity) {
    var UrbanAreas = getUrbanAreas(viewModel, activity)

    LazyColumn(){
        items(items = UrbanAreas){ area ->
            CustomArea(viewModel,activity,area)
        }
    }
}

@Composable
fun getUrbanAreas(viewModel: MainViewModel,activity:MainActivity): SnapshotStateList<Ua_item>{
    var UrbanAreas = remember { mutableStateListOf<Ua_item>()}
    viewModel.getPost()
    viewModel.myResponse.observe(activity, Observer{ response ->
        Log.d("Response",response.body()?.Links?.ua_item.toString())
        response.body()?.Links?.ua_item?.forEach {
            UrbanAreas.add(it)
        }
    })
    return UrbanAreas
}

@Composable
fun getImageHref(name:String,viewModel: MainViewModel,activity:MainActivity,uaLocator:String): String {

    var imageHref:String by remember{ mutableStateOf("")}
    viewModel.getPost2(uaLocator)
    viewModel.myResponseHref.observe(activity, Observer{ response ->
        Log.d("Response image link "+name,response.body()?.photos?.toString()!!)
        imageHref = response.body()?.photos?.get(0)?.image?.mobile.toString()
    })
    return imageHref
}


@Composable
fun CustomArea(viewModel: MainViewModel,activity:MainActivity,area:Ua_item) {
    var isSelected by remember { mutableStateOf(false) }
    var uaLocator:String= area.href?.replace("https://api.teleport.org/api/urban_areas/","").toString()
    var imageHref by remember { mutableStateOf("") }

    Text(
        text = area.name!!,
        modifier = Modifier

            .clickable {
                isSelected = !isSelected
                // Add your onClick logic here
            }
            .padding(16.dp)
            .background(if (isSelected) Color.Gray else Color.Transparent)
            .fillMaxWidth(),
        fontSize = 20.sp
    )
    if (isSelected){
        imageHref = getImageHref(area.name!!,viewModel, activity, uaLocator)
        AsyncImage(
            model = imageHref,
            contentDescription = "Image"
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val repository = Repository()
    val viewModelFactory = MainViewModelFactory(repository)
    //val viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
    LAB_06_PMTheme {
        //Greeting(viewModel,this)
    }
}