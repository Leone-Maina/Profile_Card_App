package com.example.profilecard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.profilecard.ui.theme.ProfileCardTheme
import com.example.profilecard.ui.theme.lightGreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileCardTheme {
                UsersApplication()
            }
        }
    }
}

@Composable
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list") {
            UserListScreen(userProfiles, navController)
        }

        composable(route = "user_details/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
        })) {navBackStackEntry ->
            UserProfileDetailsScreen(navBackStackEntry.arguments!!.getInt("userId"), navController)
        }
    }
}

@Composable
fun UserListScreen(userProfiles: List<UserProfile>, navController: NavHostController?) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(title = "Users List", icon = Icons.Default.Home)

        LazyColumn {
            items(userProfiles) { userProfile ->
                ProfileCard(userProfile = userProfile) {
                    navController?.navigate("user_details/${userProfile.id}")
                }
            }
        }
    }
}

@Composable
fun UserProfileDetailsScreen(userId: Int, navController: NavHostController?) {
    val userProfile = remember {
        userProfileList.first {
            it.id == userId
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AppBar(title = "User Profile Details", icon = Icons.Default.ArrowBack) {
            //TODO navigate back
            navController?.navigateUp()
        }

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            ProfilePicture(userProfile.drawableId, userProfile.status, 240.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit = { }) {
    TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Cyan),
        navigationIcon = {
            Icon(imageVector = icon, "Icon",
            modifier = Modifier.padding(horizontal = 12.dp)
                .clickable(onClick = { iconClickAction.invoke() })) },

        title = {
            Text(text = title)
        }
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile, clickAction : () -> Unit) {
    Card(modifier = Modifier
        .padding(
            top = 8.dp,
            bottom = 4.dp,
            start = 16.dp,
            end = 16.dp
        )
        .fillMaxWidth()
        .wrapContentHeight(align = Alignment.Top)
        .clickable(onClick = { clickAction.invoke() }),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            ProfilePicture(userProfile.drawableId, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(drawableId: Int, onlineStatus: Boolean, imageSize: Dp) {
    Card (shape = CircleShape,
        border = BorderStroke(width = 2.dp,
            color = if (onlineStatus) {
                MaterialTheme.colorScheme.lightGreen
            }

            else {
                Color.Red
            }),

        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
        Image(painterResource(id = drawableId),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(imageSize),
            contentScale = ContentScale.Crop)

//        AsyncImage(data = pictureUrl,
//            contentDescription = "Profile Picture",
//            modifier = Modifier.size(72.dp),
//            contentScale = ContentScale.Crop
//        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {
    Column(modifier = Modifier.padding(8.dp),
        horizontalAlignment = alignment) {
        Text(text = userName,
            style = MaterialTheme.typography.headlineMedium,
            color = if (onlineStatus) {
                MaterialTheme.colorScheme.onSurface
            }

            else {
                Color.Gray.copy(alpha = 1.0f)
            })

        Text(text = if (onlineStatus) {
            "Active now"
        }

        else {
            "Offline"
        },
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray.copy(alpha = 0.8f)) //this line grays out the text "Active now"
    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    ProfileCardTheme {
        UserListScreen(userProfiles = userProfileList, null)
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileDetailsPreview() {
    ProfileCardTheme {
        UserProfileDetailsScreen(userId = 0, null)
    }
}