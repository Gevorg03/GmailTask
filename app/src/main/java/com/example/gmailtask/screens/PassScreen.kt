package com.example.gmailtask.screens

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gmailtask.R
import com.example.gmailtask.ui.theme.LightBlue
import com.example.gmailtask.viewModels.PassViewModel

@Composable
fun PassScreen(
    navController: NavController,
    user: String?,
    context: Context
) {
    val viewModel: PassViewModel = viewModel()

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
            Welcome(user)
            PassField(viewModel)
            Spacer(modifier = Modifier.height(50.dp))
            Forgot(text = stringResource(id = R.string.forgot_pass))

        }

        NextButton(
            navController = navController,
            viewModel = viewModel,
            context = context
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Welcome(
    user: String?
) {
    Text(
        text = stringResource(id = R.string.welcome),
        fontSize = 22.sp
    )

    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "person"
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = user.toString(),
            fontSize = 15.sp,
            color = LightBlue,
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassField(
    viewModel: PassViewModel
) {
    val pass = remember {
        mutableStateOf(viewModel.pass.value)
    }

    val passwordVisibility = remember {
        mutableStateOf(viewModel.isShowed.value)
    }

    val isClicked = remember {
        mutableStateOf(false)
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
    ) {
        OutlinedTextField(
            value = pass.value,
            onValueChange = {
                pass.value = it
                viewModel.setPass(it)
                isClicked.value = true
            },
            label = {
                Text(
                    text = stringResource(id = R.string.pass),
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
                if (pass.value.isNotEmpty()) Color.Black
                else Color.Red,
            ),
            singleLine = true,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth(),

            supportingText = {
                println(isClicked)
                if (pass.value.isEmpty() && !isClicked.value)
                    IsValid(
                        stringResource(id = R.string.enter_pass),
                        isValid = true,
                        viewModel = viewModel
                    )
                else if (pass.value.isEmpty())
                    IsValid(
                        stringResource(id = R.string.enter_pass),
                        isValid = false,
                        viewModel = viewModel
                    )
            },
            visualTransformation =if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
        )

    }

    ShowPassword(
        viewModel = viewModel,
        passwordVisible = passwordVisibility
    )

}

@Composable
fun ShowPassword(
    viewModel: PassViewModel,
    passwordVisible: MutableState<Boolean>
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = passwordVisible.value,
            onCheckedChange = {
                passwordVisible.value = it
                viewModel.setShowed(it)
            },
        )
        Text(
            text = "Show Password"
        )
    }
}
