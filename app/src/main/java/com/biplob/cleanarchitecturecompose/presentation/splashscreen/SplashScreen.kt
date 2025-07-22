import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SplashScreen(
    loading: Boolean,
    error: String?,
    onRetry: () -> Unit
) {
    Log.d("SplashScreen inside class", "Composed! loading=$loading, error=$error")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            Log.d("SplashScreen", "Loading UI shown")
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(24.dp))
                Text("Loading Users...", style = MaterialTheme.typography.h6)
            }
        } else if (error != null) {
            Log.d("SplashScreen", "Error UI shown: $error")
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(error, color = MaterialTheme.colors.error)
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = onRetry) { Text("Retry") }
            }
        }
    }
}
