package com.example.booktrackerapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.booktrackerapp.ui.screens.*
import com.example.booktrackerapp.viewmodel.BookViewModel

@Composable
fun AppNavigation(viewModel: BookViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list", modifier = modifier) {
        composable("list") {
            BookListScreen(
                viewModel = viewModel,
                onNavigateToAdd = { navController.navigate("add") },
                onNavigateToDetail = { id -> navController.navigate("detail/$id") }
            )
        }
        composable("add") {
            AddBookScreen(viewModel, onBack = { navController.popBackStack() })
        }
        composable(
            "detail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("bookId") ?: 0
            BookDetailScreen(id, viewModel)
        }
    }
}