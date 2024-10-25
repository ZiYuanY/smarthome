import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smarthome.AdminScreen
import com.example.smarthome.HomeScreen
import com.example.smarthome.VideoPlayerScreen
import com.example.smarthome.LoginScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("admin") {
            AdminScreen(navController)
        }
        composable("video") {
            VideoPlayerScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
    }
}








