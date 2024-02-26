package com.onedeveloper.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.onedeveloper.jetpackcompose.screens.QuoteDetailScreen
import com.onedeveloper.jetpackcompose.screens.QuoteListScreen
import com.onedeveloper.jetpackcompose.utils.Pages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            DataManager.loadAssets(applicationContext)
        }
        setContent {
            App()
        }
    }
}

@Composable
private fun App() {
    if (DataManager.isDataisLoaded.value) {
        if(DataManager.currentPage.value == Pages.LISTING){
            QuoteListScreen(data = DataManager.data) {
                DataManager.switchPages(it)
            }
        }
        else{
            DataManager.currentQuote?.let { QuoteDetailScreen(it) }
        }

    } else {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize(1f)) {
            Text(text = "Loading.....", style = MaterialTheme.typography.headlineSmall)
        }
    }
}