package com.example.loginjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginjetpackcompose.ui.theme.MastterClassComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "Login"){
                composable("Login"){
                    LoginForm(onLogin = {
                        navController.navigate("main")
                    })
                }
                composable("Main"){
                    Main()
                }
            }


        }
    }
}
@Preview
@Composable
fun Main(){
    Screen {
        Box(
            contentAlignment = Alignment.Center
        ) {
             Text(
                 text = "Main Screen",
                 style = MaterialTheme.typography.titleLarge,
                 )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(onLogin: () -> Unit) {
    Screen{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            var user by remember { mutableStateOf("") }
            var passwd by remember { mutableStateOf("") }
            val buttonEnable = user.isNotEmpty() && passwd.isNotEmpty()
            UserField(user, onValueChange = { user = it})
            PasswdField(passwd, onValueChange = { passwd = it})
            ButtonLogin(buttonEnable, onLogin)
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserField( value: String, onValueChange: (String) -> Unit,){

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange ,
        label = { Text(text = "User")}
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswdField( value: String, onValueChange: (String) -> Unit,){
var passVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = value,
        onValueChange =  onValueChange ,
        label = { Text(text = "Password")},
        visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
             IconToggleButton(checked = passVisible, onCheckedChange = {passVisible = it}) {
                 val icon = if(passVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                 Icon(imageVector = icon, contentDescription = null)
             }
        }
    )
}
@Composable
fun ButtonLogin(enable: Boolean, onLogin: () -> Unit){
    Button(onClick =  onLogin ,
        enabled = enable) {
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null )
        Text(text = "Login")
    }
}
@Composable
fun Screen(content: @Composable () -> Unit){
    MastterClassComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

