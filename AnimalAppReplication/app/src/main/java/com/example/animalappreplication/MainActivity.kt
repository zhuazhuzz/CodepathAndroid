package com.example.animalappreplication

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.animalappreplication.ui.theme.AnimalAppReplicationTheme
import android.view.LayoutInflater
import android.view.ViewGroup


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_view)

        val textviewLayout = LayoutInflater.from(this).inflate(R.layout.text_view, findViewById(R.id.allViews) as ViewGroup, false)

        val mainLayout = findViewById<ViewGroup>(R.id.allViews)
        mainLayout.addView(textviewLayout)

    }
}






//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AnimalAppReplicationTheme {
//        Greeting("Android")
//    }
//}