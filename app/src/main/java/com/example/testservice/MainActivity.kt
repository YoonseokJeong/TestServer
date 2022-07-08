package com.example.testservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.testservice.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val url = "https://287a-192-249-19-234.jp.ngrok.io"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var server = retrofit.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.buttonGet.setOnClickListener {
            server.getRequest("name").enqueue(object: Callback<ResponseDC> {
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
                    Log.e("response : ", t.toString())
                }

                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }

            })
        }

        binding.buttonPost.setOnClickListener {
            server.postRequest("id", "password").enqueue((object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }
            }))
        }
        binding.buttonUpdate.setOnClickListener {
            server.putRequest("board", "내용").enqueue((object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }
            }))
        }
        binding.buttonDelete.setOnClickListener {
            server.deleteRequest("board").enqueue((object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {

                }
                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }
            }))
        }
    }
}