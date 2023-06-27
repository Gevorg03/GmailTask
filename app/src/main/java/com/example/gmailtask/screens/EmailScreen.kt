package com.example.gmailtask.screens

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gmailtask.R
import com.example.gmailtask.navigation.StartScreen
import com.example.gmailtask.ui.theme.ColorGoogleBlue
import com.example.gmailtask.ui.theme.ColorGoogleGreen
import com.example.gmailtask.ui.theme.ColorGoogleRed
import com.example.gmailtask.ui.theme.ColorGoogleYellow
import com.example.gmailtask.ui.theme.LightBlue
import com.example.gmailtask.viewModels.LoginViewModel
import com.example.gmailtask.viewModels.PassViewModel


@Composable
fun EmailScreen(
    navController: NavController,
) {
    val viewModel: LoginViewModel = viewModel()

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 50.dp)
        ) {
            GoogleHeader()
            Title()
            Spacer(modifier = Modifier.height(10.dp))
            LoginFields(viewModel = viewModel)
            Forgot(stringResource(id = R.string.forgot_email))
            Spacer(modifier = Modifier.height(50.dp))
            CreateAccount()
        }

        NextButton(
            navController = navController,
            viewModel = viewModel,
            context = null
        )
    }
}

@Composable
fun GoogleHeader() {
    val text = "Google"
    val colors = listOf(
        ColorGoogleBlue, ColorGoogleRed, ColorGoogleYellow,
        ColorGoogleBlue, ColorGoogleGreen, ColorGoogleRed
    )
    Text(
        text = buildAnnotatedString {
            text.forEachIndexed { index, letter ->
                withStyle(style = SpanStyle(color = colors[index])) {
                    append(letter)
                }
            }
        },
        fontSize = 20.sp
    )

}

@Composable
fun Title() {
    Text(
        text = stringResource(id = R.string.sign_in),
        fontSize = 22.sp
    )

    Row {
        Text(
            text = stringResource(id = R.string.sign_in_with_google),
            fontSize = 15.sp
        )
        Text(
            text = stringResource(id = R.string.learn_more),
            fontSize = 15.sp,
            color = LightBlue,
            modifier = Modifier
                .padding(start = 5.dp)
                .clickable { }

        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginFields(
    viewModel: LoginViewModel
) {

    val email = remember {
        mutableStateOf(viewModel.email.value)
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    ) {
        OutlinedTextField(
            value = email.value,
            onValueChange = {
                email.value = it
                viewModel.login(it)
            },
            label = {
                Text(
                    text = stringResource(id = R.string.email_or_phone),
                    color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
            },

            textStyle = TextStyle(
                color = if (isSystemInDarkTheme()) Color.White
                else Color.Black
            ),

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor =
                if (isEmailValid(viewModel.email.value)) Color.Black
                else Color.Red,
//                unfocusedBorderColor =
//                if (isEmailValid(viewModel.email.value)) Color.Black
//                else Color.Red,
            ),
            singleLine = true,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(),

            supportingText = {
                println(isEmailValid(viewModel.email.value))
                if (isEmailValid(viewModel.email.value) || viewModel.email.value.isEmpty()) {
                    IsValid(
                        stringResource(id = R.string.not_find_acc),
                        isValid = true,
                        viewModel = viewModel
                    )
                } else {
                    IsValid(
                        stringResource(id = R.string.not_find_acc),
                        isValid = false,
                        viewModel = viewModel
                    )
                }
            }
        )

    }
}

@Composable
fun Forgot(
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            color = LightBlue,
            modifier = Modifier
                .clickable { }

        )
    }
}

@Composable
fun CreateAccount() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.create_account),
            fontSize = 15.sp,
            color = LightBlue,
            modifier = Modifier
                .clickable { }
                .padding(bottom = 10.dp)
        )
    }
}

@Composable
fun NextButton(
    navController: NavController,
    viewModel: ViewModel,
    context: Context?
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, bottom = 30.dp, end = 20.dp)
    ) {
        Button(
            onClick = {
                next(
                    navController = navController,
                    viewModel = viewModel,
                    context = context
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = LightBlue
            ),
        ) {
            Text(
                text = stringResource(id = R.string.btn_next),
            )
        }
    }

}

private fun next(
    navController: NavController,
    viewModel: ViewModel,
    context: Context?
) {
    if (viewModel is LoginViewModel) {
        if (!viewModel.error.value && viewModel.email.value.isNotEmpty()) {
            navController.navigate(StartScreen.Pass.route)
        }
    } else if (viewModel is PassViewModel) {
        if (viewModel.pass.value.isNotEmpty()) {
            if (context != null) {
                val sharedPreferences: SharedPreferences =
                    context.getSharedPreferences("UserInfo", MODE_PRIVATE)
                val myEdit = sharedPreferences.edit()

                myEdit.putString("email", "user")
                myEdit.putString("pass", viewModel.pass.value)
                myEdit.apply()
            }
            navController.navigate(StartScreen.Main.route)
        }
    }
}

@Composable
fun IsValid(
    text: String,
    isValid: Boolean,
    viewModel: ViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "error",
            tint =
            if (!isValid) Color.Red
            else {
                if (isSystemInDarkTheme()) Color.Transparent
                else Color.White
            }
        )
        Spacer(
            modifier = Modifier
                .width(5.dp)
        )
        Text(
            text =
            if (!isValid) text
            else "",
            color = Color.Red
        )
        if (viewModel is LoginViewModel) {
            if (isValid)
                viewModel.error(false)
            else
                viewModel.error(true)
        }
    }
}

private fun isEmailValid(email: String): Boolean {
    val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+\$")
    return email.matches(emailRegex)
}