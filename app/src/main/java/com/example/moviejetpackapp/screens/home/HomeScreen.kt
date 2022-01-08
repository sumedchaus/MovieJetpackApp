package com.example.moviejetpackapp.screens.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.moviejetpackapp.model.Movie
import com.example.moviejetpackapp.model.getMovies
import com.example.moviejetpackapp.navigation.MovieScreens

@ExperimentalAnimationApi
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Movies")
            }
        }
    ) {
        MainContent(navController = navController)

    }
}


@ExperimentalAnimationApi
@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {

    LazyColumn {
        items(items = movieList) {
            MovieRow(movie = it) { movie ->
                Log.d("Tag", "MainContent : $movie")
                navController.navigate(route = MovieScreens.DetailScreen.name + "$movie")


            }
        }
    }

}

@ExperimentalAnimationApi
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)

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

                Image(painter = rememberImagePainter(data = movie.images[0],
                    builder = {
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }
                ),
                    contentDescription = "movie poster")


            }
            Column() {

                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = movie.year,
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = movie.genre,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold

                )

                AnimatedVisibility(visible = expanded) {
                    Column {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.Bold,

                                        fontSize = 13.sp
                                    )
                                ) {
                                    append("Plot:  ")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        Color.DarkGray,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                ) {
                                    append(movie.plot)
                                }
                            },
                            modifier = Modifier
                                .padding(6.dp)
                        )
                        Divider()
                        Text(
                            "Director:  ${movie.director}",
                            style = MaterialTheme.typography.caption,
                            fontWeight = FontWeight.Bold

                        )
                        Text(
                            "Actors:  ${movie.actors}",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.caption
                        )
                        Text(
                            "Rating:  ${movie.rating}",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.caption
                        )

                    }
                }

                Icon(
                    imageVector = if (expanded) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                    tint = Color.DarkGray
                )

            }

        }

    }

}



