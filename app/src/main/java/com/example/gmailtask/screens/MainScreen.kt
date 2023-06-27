package com.example.gmailtask.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gmailtask.R
import com.example.gmailtask.bottomBarNavigation.BottomBarScreen
import com.example.gmailtask.ui.theme.LightBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        SampleAppNavGraph(navHostController = navController)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    navHostController: NavHostController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 2.dp, bottom = 56.dp),
    ) {
        item {
            TopAppBar(coroutineScope, drawerState)
        }
        items(20) {
            Emails()
        }
    }

    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.fillMaxHeight()
    ) {
        BottomBar(navController = navHostController)
    }
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 65.dp)
    ) {
        ExtendedFloatingActionButton(
            text = {
                Text(text = "Navigate", color = Color.White)
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pencil),
                    contentDescription = "Compose",
                    tint = Color.White,
                )
            },
            onClick = { },
            contentColor = LightBlue,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    coroutineScope: CoroutineScope,
    drawerState: DrawerState
) {
    TopAppBar(
        title = { },
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape),
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch { drawerState.open() }
            }, content = {
                Icon(
                    imageVector = Icons.Default.Menu, contentDescription = null
                )
            })
        },
        actions = {
            Search()
            AppBarIcon(
                icon = R.drawable.ic_person,
                description = "user"
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.LightGray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search() {
    val text = remember {
        mutableStateOf("")
    }

    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                color = if (isSystemInDarkTheme()) Color.White
                else Color.Black
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun AppBarIcon(
    @DrawableRes icon: Int,
    description: String
) {
    IconButton(
        onClick = {}
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = description,
        )
    }
}

@Composable
fun Emails() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp),
    ) {
        EmailIcon()
        EmailDetails(
            "Google",
            "Have a good day"
        )
    }
}

@Composable
fun EmailIcon() {
    Box(
        modifier = Modifier
            .padding(top = 5.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.google),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )

    }
}

@Composable
fun EmailDetails(
    from: String,
    title: String
) {
    Column(
        modifier = Modifier
            .padding(start = 15.dp, top = 5.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = from,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 20.sp,
            )

            Text(
                text = "27 Jun",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 15.sp,
            )
        }

        Description(
            title = title
        )
    }
}

@Composable
fun Description(
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
        fontSize = 12.sp,
        modifier = Modifier
            .padding(top = 5.dp)
    )
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Meet,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        backgroundColor =
        if (isSystemInDarkTheme()) Color.Black
        else Color.LightGray,
        modifier = Modifier.fillMaxWidth()
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
) {
    val selected = currentDestination?.hierarchy
        ?.any { it.route == screen.route } == true

    val contentColor = if (selected) LightBlue else Color.White

    BottomNavigationItem(
        icon = {
            if (screen.route != "Home") {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = "Navigation Icon",
                    tint = contentColor
                )
            } else {
                BadgedBox(
                    badge = {
                        Badge {
                            Text(
                                text = "99+",
                                color = Color.White,
                                modifier = Modifier
                                    .background(Color.Red)
                            )
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "Navigation Icon",
                        tint = contentColor
                    )
                }
            }
        },
        selected = selected,
        unselectedContentColor = LocalContentColor
            .current.copy(alpha = ContentAlpha.disabled),
        selectedContentColor = LightBlue,
        onClick = {}
    )
}