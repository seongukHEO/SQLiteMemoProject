package kr.co.lion.android01.newmemoproject_seonguk

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import kr.co.lion.android01.newmemoproject_seonguk.databinding.FragmentModifyBinding
import java.time.LocalDate

class ModifyFragment : Fragment() {

    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentModifyBinding = FragmentModifyBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        initView()
        setEvent()
        return fragmentModifyBinding.root
    }
    //툴바 설정
    fun setToolBar(){
        fragmentModifyBinding.apply {
            materialToolbar6.apply {
                title = "ModifyMemo"
                //아이콘
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.MODIFY_FRAGMENT)
                }
            }
        }
    }

    //정보를 받아와서 보여준다
    fun initView(){
        fragmentModifyBinding.apply {
            var memo = arguments?.getInt("idx")
            if (memo != null){
                var memoModel = MemoDAO.selctOneMemo(mainActivity, memo)
                //Log.e("test123", "${memoModel.title}")
                titlemodifyText.setText("${memoModel.title}")
                no.setText("${memoModel.dateTime}")
                textModifyContents.setText("${memoModel.contents}")
                when(memoModel.important){
                    0 -> checkBox2.isChecked = true
                    1 -> checkBox2.isChecked = false
                }
            }

        }

    }

    //출력
    @RequiresApi(Build.VERSION_CODES.O)
    fun setEvent(){
        fragmentModifyBinding.apply {
            medifyButton.setOnClickListener {
                var memo = arguments?.getInt("idx")!!
                var contents = textModifyContents.text.toString()
                var important = if (checkBox2.isChecked){
                    Important.IMPORTANT_OK.num
                }else{
                    Important.IMPORTANT_NO.num
                }
                var dateTime = LocalDate.now().toString()
                var title = titlemodifyText.text.toString()

                var memoList = Trip(1, title, contents, important, dateTime)

                //삭제한다
                MemoDAO.deleteMemo(mainActivity, memo)
                //저장한다
                MemoDAO.insertMemo(mainActivity, memoList)

                //upDate를 사용할 줄 몰라 삭제 저장으로 대체한다


                var bundle = Bundle()
                bundle.putInt("idx", memoList.idx)
                mainActivity.replaceFragment(FragmentName.MAIN_FRAGMENT, true, true, bundle)
                enum.hideSoftInput(mainActivity)


            }
        }
    }
}


































