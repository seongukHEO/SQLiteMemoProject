package kr.co.lion.android01.newmemoproject_seonguk

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import kr.co.lion.android01.newmemoproject_seonguk.databinding.FragmentInputBinding
import java.time.LocalDate

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()

        return fragmentInputBinding.root
    }
    //툴바 구성
    @RequiresApi(Build.VERSION_CODES.O)
    fun setToolBar(){
        fragmentInputBinding.apply {
            //툴바부터
            materialToolbar2.apply {
                //타이틀
                title = "InputMemo"
            }
            //바텀바
            bottomAppBar.apply {
                //아이콘
                setNavigationIcon(R.drawable.arrow_back_24px)
                //클릭하면?
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
                }
                //메뉴
                inflateMenu(R.menu.input_menu)
                //메뉴를 눌렀을 때
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.add_menu -> {
                            checkOK()
                        }
                    }
                    true
                }
            }
        }
    }

    //입력을 받는다
    @RequiresApi(Build.VERSION_CODES.O)
    fun getData(){
        fragmentInputBinding.apply {
            var title = titleTextEdit.text.toString()
            var contents = contentsTextEdit.text.toString()
            var important = if (checkBox.isChecked){
                Important.IMPORTANT_OK.num
            }else{
                Important.IMPORTANT_NO.num
            }
            var dateTime = LocalDate.now().toString()



            var memoList = Trip(0, title, contents, important, dateTime)
            //저장해준다
            MemoDAO.insertMemo(mainActivity, memoList)
        }

    }
    //유효성 검사
    @RequiresApi(Build.VERSION_CODES.O)
    fun checkOK(){
        fragmentInputBinding.apply {
            var title = titleTextEdit.text.toString()
            var contents = contentsTextEdit.text.toString()
            if (title.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "제목 입력 오류", "제목을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(titleTextEdit, mainActivity)
                }
                return
            }else{
                enum.hideSoftInput(mainActivity)
            }
            if (contents.trim().isEmpty()){
                enum.showDiaLog(mainActivity, "내용 입력 오류", "내용을 입력해주세요"){ dialogInterface: DialogInterface, i: Int ->
                    enum.showSoftInput(contentsTextEdit, mainActivity)
                }
                return
            }else{
                enum.hideSoftInput(mainActivity)
            }
        }
        getData()
        mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
    }

}











































