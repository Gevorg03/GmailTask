package com.example.gmailtask.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gmailtask.utils.NavDrawerDestinations
import com.example.gmailtask.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    modifier: Modifier = Modifier,
    closeDrawer: () -> Unit = {}
) {
    ModalDrawerSheet(modifier = Modifier) {
        DrawerHeader(modifier)

        val items = listOf(
            NavDrawerDestinations.ALL_INBOXES, NavDrawerDestinations.PRIMARY,
            NavDrawerDestinations.PROMOTIONS, NavDrawerDestinations.SOCIAL
        )
        items.forEach { text ->
            DrawerItem(
                text = text,
                closeDrawer = closeDrawer
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(
    text: String,
    closeDrawer: () -> Unit = {}
) {
    NavigationDrawerItem(
        label = {
            Text(
                text = text,
                style = MaterialTheme.typography.labelSmall
            )
        },
        selected = text == "Primary",
        onClick = {
            closeDrawer()
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null
            )
        },
    )
}

@Composable
fun DrawerHeader(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.gmail),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(10.dp)
        )
        Divider()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleAppNavGraph(
    navHostController: NavHostController,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState =
        rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    ModalNavigationDrawer(
        drawerContent = {
            AppDrawer(
                closeDrawer = {
                    coroutineScope.launch { drawerState.close() }
                },
            )
        },
        drawerState = drawerState
    ) {
        Header(
            coroutineScope, drawerState, navHostController
        )
    }
}
