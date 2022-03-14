package com.example.getapiappications

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private var textViewResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       textViewResult = findViewById(R.id.text_view_result)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi::class.java)

        val call: Call<List<Post?>?>? = jsonPlaceHolderApi.posts

        call!!.enqueue(object : Callback<List<Post?>?> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<Post?>?>?, response: Response<List<Post?>?>) {
                if (!response.isSuccessful()) {
                    textViewResult!!.setText("Code: " + response.code())
                    return
                }
                val posts: List<Post> = response.body() as List<Post>
                for (post in posts) {
                    var content = ""
                    content += """
                ID: ${post.getId()}
                
                """.trimIndent()
                    content += """
                User ID: ${post.getUserId()}
                
                """.trimIndent()
                    content += """
                Title: ${post.getTitle()}
                
                """.trimIndent()
                    content += """
                Text: ${post.getText()}
                
                
                """.trimIndent()
                    textViewResult!!.append(content)
                }
            }

            override fun onFailure(call: Call<List<Post?>?>?, t: Throwable) {
                textViewResult!!.text = t.message
            }
        })



    }
}