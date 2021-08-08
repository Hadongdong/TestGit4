package com.example.noticesubscribe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticesubscribe.databinding.ActivityKeywordEditBinding
import com.example.noticesubscribe.databinding.ActivityNoticeClickBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NoticeClickActivity : AppCompatActivity() {

    private var noticeClickBinding: ActivityNoticeClickBinding? = null
    private val binding get() = noticeClickBinding!!
    val db= FirebaseFirestore.getInstance()
    val textList = arrayListOf<Text>()
    val textadapter = TextAdapter(textList)
    //intent를 keywordadapter.kt로부터 전달받기 위한 코드 - 0808작성
//    var dateOfaNotice = intent.getStringExtra("data")
//    var visitedOfaNotice = intent.getStringExtra("visited")
//    var linkOfaNotice = intent.getStringExtra("link")
//    var contentOfaNotice = intent.getStringExtra("content")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noticeClickBinding = ActivityNoticeClickBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // 밑의 코드는 단순 확인용
//        if (titleOfaNotice != null){
//            Log.d("notnull", "눌이 아니다")
//        } else{
//            Log.d("notnull", "눌이다")
//
//        }
        var titleOfaNotice = intent?.getStringExtra("title")
        binding.NoticeTitle.text = titleOfaNotice
        var title=binding.NoticeTitle.text.toString()
       // val title: TextView = view.findViewById(R.id.NoticeTitle)
        //차후에 내용 받아오면 이거 지우면 된다
        //binding.NoticeContent = contentOfaNotice
        val searchOption="title"
        binding.NoticeContent.adapter=textadapter
        binding.NoticeContent.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        //binding?.NoticeContent?.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
       // binding?.NoticeContent.adapter = NoticeAdapter(view.context, noticeList)
        (binding.NoticeContent.adapter as TextAdapter).search(title,searchOption)
        //(binding?.NoticeContent.adapter as NoticeAdapter).load()/
    }
    //관련공지사항 내용 나옴
    fun  TextAdapter.search(title:String,option: String){
        db.collection("total")   // 작업할 컬렉션
            .orderBy("date", Query.Direction.DESCENDING)
            .get()      // 문서 가져오기
            .addOnSuccessListener { result ->
                // 성공할 경우
                textList.clear()
                for (document in result) {  // 가져온 문서들은 result에 들어감
                    if (document.getString(option)!!.contains(title)) {
                        val item = Text(document["text"] as String)
                        textList.add(item)
                    }
                }
                notifyDataSetChanged()  // 리사이클러 뷰 갱신
            }
            .addOnFailureListener { exception ->
                // 실패할 경우
                Log.w("MainActivity", "Error getting documents: $exception")
            }
    }
}


