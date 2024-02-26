package com.onedeveloper.jetpackcompose

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.google.gson.Gson
import com.onedeveloper.jetpackcompose.models.Quote
import com.onedeveloper.jetpackcompose.utils.Pages

object DataManager {
    var data = emptyArray<Quote>()
    var isDataisLoaded = mutableStateOf(false)
    var currentQuote:Quote? = null
    var currentPage = mutableStateOf(Pages.LISTING)
    fun loadAssets(context: Context){
        val inputStream =context.assets.open("quotes.json")
        val size:Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        data = gson.fromJson(json,Array<Quote>::class.java)
        isDataisLoaded.value = true

    }

    fun switchPages(quote: Quote?){
        if(currentPage.value == Pages.LISTING){
            currentQuote = quote
            currentPage.value = Pages.DETAIL
        }
        else{
            currentPage.value = Pages.LISTING
            }
    }
}