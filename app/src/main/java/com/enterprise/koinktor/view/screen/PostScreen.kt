package com.enterprise.koinktor.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.enterprise.koinktor.R
import com.enterprise.koinktor.model.Post
import com.enterprise.koinktor.viewmodel.PostViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    viewModel: PostViewModel = koinViewModel()
) {
    val posts by viewModel.posts.collectAsStateWithLifecycle()
    val loading by viewModel.loading.collectAsStateWithLifecycle()
    val isNoInternetConnectionDialogVisible by viewModel.isNoInternetConnectionDialogVisible.collectAsStateWithLifecycle()

    if(isNoInternetConnectionDialogVisible){

        NoInternetConnectionDialog(
            onNeutralButtonClick = {
                viewModel.updateNoInternetConnectionDialogVisible(newValue = false)
            }
        )

    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Koin + Ktor + Jetpack Compose") })
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (loading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(posts) { post ->
                        PostItem(post)
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(post.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(post.body, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoInternetConnectionDialog(onNeutralButtonClick: () -> Unit) {

    BasicAlertDialog(onDismissRequest = {
        // Dismiss the dialog when the user clicks outside the dialog or on the back button.
        //isDialogVisible.value = false
    }){

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentHeight()
                .wrapContentWidth()
                .background(color = Color.White, RoundedCornerShape(size = 15.dp))
                .border(width = 3.dp, color = Color.Green, RoundedCornerShape(size = 15.dp))
                .padding(15.dp)){

            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()){

                Icon(imageVector = Icons.Default.Info,
                    contentDescription
                    = stringResource(id = R.string.no_internet_connection_popup_icon_content_description),
                    tint = Color.Blue)

                Spacer(modifier = Modifier.width(5.dp))

                Text(text = stringResource(id = R.string.no_internet_connection_popup_title))


            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = stringResource(id = R.string.no_internet_connection_popup_message))

            Spacer(modifier = Modifier.height(15.dp))

            Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                onClick = {

                    onNeutralButtonClick()

                }) {

                Text(text = stringResource(id = R.string.no_internet_connection_neutral_button_text))

            }

        }

    }

}