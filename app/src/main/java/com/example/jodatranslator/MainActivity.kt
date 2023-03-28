package com.example.jodatranslator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val translateBaseUrl = "https://api.funtranslations.com"

private val retrofit = Retrofit.Builder()
    .baseUrl(translateBaseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val translationService = retrofit.create(TranslationApi::class.java)

fun yodaTranslate(text: String) {
    translationService
        .translateToYoda(TranslationRequest(text))
        .enqueue(object : Callback<TranslationResponse> {
            override fun onResponse(
                call: Call<TranslationResponse>,
                response: Response<TranslationResponse>
            ) {
                Log.d("TRANSLATION_LOG", "Yoda says: ${response.body()?.contents?.translated}")
            }

            override fun onFailure(call: Call<TranslationResponse>, t: Throwable) {
            }

        })
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        yodaTranslate("Creating Android applications is very easy and interesting!")
    }
}