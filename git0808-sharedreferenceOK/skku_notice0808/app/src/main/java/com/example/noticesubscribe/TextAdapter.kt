package com.example.noticesubscribe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TextAdapter(val textList:ArrayList<Text>): RecyclerView.Adapter<TextAdapter.ViewHolder>() {


    //ViewHolder객체 만듦
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAdapter.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
        return ViewHolder(view)
    }


    //생성된 뷰홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: TextAdapter.ViewHolder, position: Int) {
        holder.text.text = textList[position].text

    }

    override fun getItemCount(): Int {
        return textList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val text: TextView =itemView.findViewById(R.id.textView3)
    }

}