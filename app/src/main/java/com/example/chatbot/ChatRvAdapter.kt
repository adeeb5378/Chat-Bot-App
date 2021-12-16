package com.example.chatbot

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

class ChatRvAdapter(private val chatModalArrayList : ArrayList<ChatModel>, context: Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){




    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val userTv:TextView=itemView.findViewById(R.id.userMsg)
    }

    class BotViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        val botTv:TextView=itemView.findViewById(R.id.botMsg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType)
        {
            1->
            {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.user_msg_rv_item,parent,false)
                return UserViewHolder(view)
            }
            else->
            {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.bot_msg_rv_item,parent,false)
                return BotViewHolder(view)
            }


        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatModel:ChatModel = chatModalArrayList[position]
        when(chatModel.sender)
        {
            "bot"->
            {
                (holder as BotViewHolder).botTv.text=chatModel.message
            }
            "user"->
            {
                (holder as UserViewHolder).userTv.text=chatModel.message
            }


        }


    }

    override fun getItemCount(): Int {
        return chatModalArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(chatModalArrayList[position].sender) {

            "bot"-> 0
            "user"-> 1
            else-> {
                -1
            }
        }
    }


}