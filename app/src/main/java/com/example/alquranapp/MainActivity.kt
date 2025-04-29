package com.example.alquranapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.alquranapp.ui.SurahDetailScreen
import com.example.alquranapp.ui.ui.SurahListScreen
import com.example.alquranapp.ui.theme.AlQuranAppTheme
import com.example.alquranapp.ui.ui.LoginScreen
import com.example.alquranapp.viewmodel.LoginViewModel
import com.example.alquranapp.viewmodel.QuranViewModel

class MainActivity : ComponentActivity() {
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlQuranAppTheme {
                QuranApp(loginViewModel)
            }
        }
    }
}

@Composable
fun QuranApp(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    val isLoggedIn by loginViewModel.isLoggedIn.collectAsState()
    val quranViewModel: QuranViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "surah_list" else "login"
    ) {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("surah_list") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                loginViewModel = loginViewModel
            )
        }
        composable("surah_list") {
            SurahListScreen(
                navController = navController,
                quranViewModel = quranViewModel // konsisten dengan nama parameter
            )
        }
        composable(
            "surah_detail/{surahNumber}",
            arguments = listOf(navArgument("surahNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            val surahNumber = backStackEntry.arguments?.getInt("surahNumber") ?: 1
            SurahDetailScreen(surahNumber = surahNumber, quranViewModel = quranViewModel) // <- tambahkan quranViewModel
        }

    }
}
