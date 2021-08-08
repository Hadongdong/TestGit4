package com.example.noticesubscribe.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noticesubscribe.MyFirebaseMessagingService
import com.example.noticesubscribe.R
import com.example.noticesubscribe.databinding.FragmentAlarmBinding
import com.example.noticesubscribe.databinding.FragmentSearchBinding

class AlarmFragment : Fragment() {
    //알림설정
    //mbinding을 통해 네비게이션 바를 이용해서 이동할 수 있는 fragment를 만듦
    private var mBinding : FragmentAlarmBinding? = null
    private fun loadData() {
        val pref = context?.getSharedPreferences("pref", 0)
        mBinding?.switch1?.isChecked = pref!!.getBoolean("alarm_TF", false) //두번째 인자는 디폴트 설정
    }

    // 알람 정보 저장하는 함수수
    private fun saveData() {
        val pref = context?.getSharedPreferences("pref", 0)
        val edit = pref?.edit() // 수정모드
        edit?.putBoolean("alarm_TF", mBinding?.switch1!!.isChecked)
        edit?.apply() // 저장 완료

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAlarmBinding.inflate(inflater,container,false)

        mBinding = binding
        R.id.bt_editKeyword
        loadData()
        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding?.switch1?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Log.d("switch", "스위치가 체크")
                saveData()
                MyFirebaseMessagingService()
            } else {
                Log.d("switch", "스위치가 해제")

                saveData()
            }
        }
    }



}