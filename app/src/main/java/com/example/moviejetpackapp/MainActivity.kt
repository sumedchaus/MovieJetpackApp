package com.example.moviejetpackapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.moviejetpackapp.navigation.MovieNavigation
import com.example.moviejetpackapp.ui.theme.MovieJetpackAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieJetpackAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyApp(
                        MovieNavigation()
                    )
                }
            }
        }
    }
}

@Composable
fun MyApp(movieNavigation: Unit) {

    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Movies")
            }
        }
    ) {
        MainContent()

    }
}

@Composable
fun MainContent(movieList: List<String> = listOf("a", "b", "c", "d","e","f","g","h")) {

    LazyColumn {
        items(items = movieList) {
            MovieRow(movie = it){movie ->
                Log.d("Tag","MainContent : $movie")


            }
        }
    }

}

@Composable
fun MovieRow(movie: String, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                onItemClick(movie)

            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Icon(imageVector = Icons.Default.AccountBox,
                    contentDescription ="Moive Image")
            }
            Text(text = movie)

        }
    }

}


























