package agency.five.codebase.android.ordermanager.ui.main

import agency.five.codebase.android.ordermanager.data.currentuser.UserDataSerializer
import agency.five.codebase.android.ordermanager.data.currentuser.UserDataViewModel
import agency.five.codebase.android.ordermanager.ui.theme.OrderManagerTheme
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.dataStore

val Context.dataStore by dataStore("user-data.json", UserDataSerializer)

class MainActivity : ComponentActivity() {
    //    private lateinit var auth: FirebaseAuth
    private val userDataViewModel: UserDataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            setContent {
                MainScreen()
            }
        }*/
        setContent {
            MainScreen(userDataViewModel = userDataViewModel)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OrderManagerTheme {
        Greeting("Android")
    }
}
