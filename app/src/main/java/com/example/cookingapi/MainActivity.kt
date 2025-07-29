package com.example.cookingapi

import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookingapi.ui.theme.CookingAPITheme
import com.example.cookingapi.databinding.LayoutBinding

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.asynchttpclient.RequestHeaders;
import com.codepath.asynchttpclient.callback.BinaryHttpResponseHandler;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;
import okhttp3.Headers
import java.util.Objects

data class Obj(val img: String, val name: String, val aisle: String, val special: String)

class MainActivity : ComponentActivity() {
    private lateinit var foodList: MutableList<Obj>
    private lateinit var foodView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        foodView = findViewById<RecyclerView>(R.id.FoodRecycleView)
        foodList = mutableListOf<Obj>()

        selectNextFood()
    }

    private fun selectNextFood() {
        val client = AsyncHttpClient()
        val params = RequestParams()
        val apiKey = "174ddc8ec3654866a20ed52ced75b78e"
        params["limit"] = "5"
        params["page"] = "0"
        val idArr = arrayOf(2047,4053,1001,19335,14412,20081,11215,1123,11282,2050,1077,1082047,9152,1145,1002030,18371,1002030,1102047,1123,19334,18372,11215,4513,19335,19296,2010,1002030,1017,1033,1022020,11124,2010,2027,10011282,1053,11143,1025006,1056,2050,1012047,11291,9160,16124,19336,11297,10123,20027,1002014,11529,4582,4582,6194,19911,11821,10011693,9156,2028,1002046,2009,19081,4025,19335,11282,11165,11297,11165,12142,23572,11216,11215,1032009,12155,2031,1034053,11124,4047,11477,9316,6971,4058,10711111,9206,11362,10115261,11529,9152,9037,1230,19334,2025,2069,2021,10511282,11216,1124,2025,1001009,11333,12061,1077,9150,15152,11887,11291,2026,20081,6172,1125,1002014,1022068,19336,11677,2044,10023572,10011457,11260,9050,98905,2049,18064,16098,2042,1055062,9160,9040,11206,9040,10311529,12104,1053,1102047,2048,2004,2004,1012047,10019146,1033,19165,11935,11677,14106,9216,12023,6164,1019,9299,6615,20420,9003,9003,19165,11304,20444,11090,2011,1053,9302,93607,2044,2049,6168,11291,2003,11507,18375,11233,1009,4582,2063,2044,16018,2015,6150,1002050,11135,11979,5062,2047,11011,1001053,1022027,11156,6970,2048,19350,1022053,1012028,20035,10019903,1159,9266,10151,9159,10014412,2014,1001026,2054,16424)

        for(i in 0 until 10) {
            var ele = idArr[i].toString()
            client["https://api.spoonacular.com/food/ingredients/$ele/information?apiKey=$apiKey&amount=1", params, object :
                JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                    val jpg: String = json.jsonObject.get("image").toString()
                    val name: String = json.jsonObject.get("name").toString()
                    val aisle: String = json.jsonObject.get("aisle").toString()
                    val texture: String = json.jsonObject.get("consistency").toString()

                    val img: String = "https://spoonacular.com/cdn/ingredients_250x250/$jpg"

                    val newObj = Obj(img, name, aisle, texture)
                    foodList.add(newObj)

                    val foodAdapter = FoodAdapter(foodList)
                    foodView.adapter = foodAdapter
                    foodView.layoutManager = LinearLayoutManager(this@MainActivity)
                    foodView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))



                }
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String,
                    throwable: Throwable?
                ) {
                }
            }]
        }




    }
}





@GlideModule
class MyAppGlideModule : AppGlideModule() {
    // leave empty for now

}
