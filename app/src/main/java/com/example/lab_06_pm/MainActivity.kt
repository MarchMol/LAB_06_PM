package com.example.lab_06_pm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lab_06_pm.api.SimpleAPI
import com.example.lab_06_pm.model.Post
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
        viewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer{ response ->
            Log.d("Response",response.name)
            Log.d("Response",response.href)
        })



        setContent {
            LAB_06_PMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(listOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"))
                }
            }
        }
    }
}

@Composable
fun Greeting(items: List<String>) {

    Column (modifier = Modifier.verticalScroll(rememberScrollState())){
        items.forEach { text ->
            var isSelected by remember { mutableStateOf(true) }
            Text(

                text = text,
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
        }
    }
}

fun conectionFun():String{
    var result: String = ""
    var connection: HttpURLConnection? = null
    var httpResult:Int =0

    try{
        val url = URL("https://api.teleport.org/api/urban_areas/")
        connection = url.openConnection() as HttpURLConnection

        connection.doOutput = true
        connection.doInput = true
        connection.instanceFollowRedirects = false
        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("charset", "utf-8")
        connection.setRequestProperty("Accept", "application/json")
        connection.useCaches = false


        httpResult = connection.responseCode

        if (httpResult == HttpURLConnection.HTTP_OK) {

            val inputStream = connection.inputStream

            val reader = BufferedReader(InputStreamReader(inputStream))
            val sb = StringBuilder()
            var line: String?
            try {
                while (reader.readLine().also { line = it } != null) {
                    sb.append(line + "\n")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {

                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            result = sb.toString()
        } else {

            result = connection.responseMessage
        }

    } catch (e: IOException) {
    e.printStackTrace()
    }


    return result

}


fun getCities(){
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.teleport.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val API = retrofit.create(SimpleAPI::class.java)
    val ListCiudades = mutableListOf<Post>()

    try{
        val call = API.getPost()
        val response = call.execute()
    } catch(e: Exception){

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LAB_06_PMTheme {
        Greeting(listOf("hola","como","estas","Pues","Si"))
    }
}