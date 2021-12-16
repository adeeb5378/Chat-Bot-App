package com.example.chatbot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.user_msg_rv_item.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    val BOT_KEY:String="bot"
    val USER_KEY:String="user"

    val chatModalArrayList=ArrayList<ChatModel>()

    private lateinit var mAdapter:ChatRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


        recycleView.layoutManager = LinearLayoutManager(this)
        mAdapter = ChatRvAdapter(chatModalArrayList,this)
        recycleView.adapter = mAdapter


        msgSend.setOnClickListener {
            if(etMsg.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please Enter Message",Toast.LENGTH_SHORT).show()
            }
            else
            {
                getResponse(etMsg.text.toString())
                etMsg.setText("")
            }
        }
    }

    private fun getResponse(message: String) {

        chatModalArrayList.add(ChatModel(message,USER_KEY))
        mAdapter.notifyDataSetChanged()



        val url:String="http://api.brainshop.ai/get?bid=156986&key=pGPnLd13Kx95gMAk&uid=[uid]&msg=$message"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val botResponse = it.getString("cnt")
                Log.i("Adeeb",botResponse.toString())
                chatModalArrayList.add(ChatModel(botResponse, BOT_KEY))
                mAdapter.notifyDataSetChanged()



            },
            {
                chatModalArrayList.add(ChatModel("Please Revert Your Question",BOT_KEY))

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)


//        val baseUrl="http://api.brainshop.ai/"
//        val retrofit= Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val retrofitApi = retrofit.create(RetrofitApi::class.java)
////        Call<MsgModel>
//        val call:Call<MsgModel> = retrofitApi.getMessage(url)
//        call.enqueue(object : Callback<MsgModel>{
//            override fun onResponse(call: Call<MsgModel>, response: Response<MsgModel>) {
//                if(response.isSuccessful)
//                {
//                    val modal =response.body()
//                    chatModalArrayList.add(ChatModel(modal?.cnt.toString(),BOT_KEY))
//
//                    mAdapter.notifyDataSetChanged()
//
//
//                }
//            }
//
//            override fun onFailure(call: Call<MsgModel>, t: Throwable) {
//                    chatModalArrayList.add(ChatModel("Please Revert Your Question",BOT_KEY))
//                    mAdapter.notifyDataSetChanged()
//
//            }
//
//        })

    }
}