import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import org.koin.compose.koinInject
import org.sample.preferencesapp.core.presentation.theme.MyAppTheme
import org.sample.preferencesapp.main.MainScreen
import org.sample.preferencesapp.main.MainViewModel

@Composable
fun App(
    mainViewModel: MainViewModel = koinInject()
) {
    val darkTheme = when (mainViewModel.appTheme.collectAsState().value) {
        1 -> true
        else -> false
    }

    MyAppTheme(
        darkTheme = darkTheme
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Navigator(screen = MainScreen())
        }
    }

}