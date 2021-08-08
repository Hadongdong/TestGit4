package com.example.noticesubscribe

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room


class NoticeAdapter( val parentContext: Context,val noticeList: ArrayList<Notice>) : RecyclerView.Adapter<NoticeAdapter.CustomViewHolder>(){
    //공지들이 표시되는 recyclerview를 사용하기 위해 만든 adpater


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): NoticeAdapter.CustomViewHolder {
        //view는 표시되는 화면을 의미
        //button은 은행잎모양 스크랩 버튼 의미
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
//        val button :Button = view.findViewById(R.id.bt_keep_button)
//        button?.setOnClickListener {
//            button?.isSelected = button?.isSelected != true
//        }

        return CustomViewHolder(view) // 이 return값을 이용해서 layout을 누르면 해당 link로 이동하게 하면 될 것 같습니다!
    }

    override fun onBindViewHolder(holder: NoticeAdapter.CustomViewHolder, position: Int) {

        holder.title.text = noticeList.get(position).title
        holder.date.text = noticeList.get(position).date
        holder.visited.text = noticeList.get(position).visited
        holder.link.text = noticeList.get(position).link
        holder.text.text = noticeList.get(position).text




        holder.itemView.setOnClickListener {
//            여기 아래 3줄은 크롬으로 바로 창을 여는 코드이다
//            Log.d("noticeadapter", "여기에서의 명령을 실행한거")
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.skku.edu/skku/campus/skk_comm/notice01.do"+holder.link.text.toString()))
//            startActivity(parentContext, intent, null)

            // 공지사항을 누르면 NoticeClickActivity 실행함
            val intent = Intent(parentContext, NoticeClickActivity::class.java).apply {
                putExtra("title", holder.title.text.toString())
//            putExtra("date", holder.date.text)
//            putExtra("visited", holder.visited.text)
//            putExtra("link", holder.link.text)
                //나중에 같은 방법으로 내용까지 넣을 때 이 주석을 사용하면 된다
                //putExtra("content", holder.content.text)
            }
            //intent에 전달할 정보를 더 넣어서 새로운 엑티비티에 건내준다 -창
//            if (holder.title.text.toString() != null){
//                Log.d("notnull", "${holder.title.text.toString()}")
//            } else{
//                Log.d("notnull", "눌이다")
//
//            }
            startActivity(parentContext, intent, null)
        }

        val innerDb = Room.databaseBuilder(
            parentContext.applicationContext,
            AppDatabase::class.java, "notice"
        ).allowMainThreadQueries().build()

        holder.scrap.setOnClickListener {
            innerDb.noticeDao().insert(noticeList.get(position))
            holder.scrap.isSelected = holder.scrap.isSelected != true
        }

    }

    override fun getItemCount(): Int {
        return noticeList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.titleView) // 제목
        val date = itemView.findViewById<TextView>(R.id.dateView) // 날짜
        val visited = itemView.findViewById<TextView>(R.id.visitedView) // 조회수
        val link = itemView.findViewById<TextView>(R.id.linkView) // 링크
        val scrap = itemView.findViewById<Button>(R.id.bt_keep_button)
        val text = itemView.findViewById<TextView>(R.id.textView)
    }


  
}