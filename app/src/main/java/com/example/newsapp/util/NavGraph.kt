package com.example.newsapp.util

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.Presentation.ArticleScreen.ArticleScreen
import com.example.newsapp.Presentation.news_screen.NewsScreen
import com.example.newsapp.Presentation.news_screen.NewsScreenViewModel


@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination ="news_screen"
    ) {

        composable(route = "news_screen") {
            val viewModel: NewsScreenViewModel = hiltViewModel()
            NewsScreen(
                states = viewModel.state,
                onEvent = viewModel::onEvent,
                onReadFull = {url->
                    navController.navigate("article_screen?web_url=$url")
                }
            )
        }
        
        composable(
            route = "article_screen?web_url={web_url}",
            arguments = listOf(navArgument(name = "web_url"){
                type= NavType.StringType
            })
        ) {
            ArticleScreen(
                url = it.arguments?.getString("web_url"),
            ) {
                navController.navigateUp()
            }
        }


    }
}

//val viewModel: NewsScreenViewModel = hiltViewModel()
//NewsScreen(
//states = viewModel.state,
//onEvent = viewModel::onEvent
//)