package kr.co.lion.android01.newmemoproject_seonguk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.newmemoproject_seonguk.databinding.FragmentInputBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setToolBar()
        getData()
        return fragmentInputBinding.root
    }
    //툴바 구성
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
                            mainActivity.removeFragment(FragmentName.INPUT_FRAGMENT)
                        }
                    }
                    true
                }
            }
        }
    }

    //입력을 받는다
    fun getData(){
        fragmentInputBinding.apply {
            var title = titleTextEdit.text.toString()
            var contents = contentsTextEdit.text.toString()
            var important = if (checkBox.isChecked){
                Important.IMPORTANT_OK.num
            }else{
                Important.IMPORTANT_NO.num
            }

            //시간을 구해준다
            var sdf = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
            var now = sdf.format(Date())


            var memoList = Trip(0, title, contents, important, now)
            //저장해준다
            MemoDAO.insertMemo(mainActivity, memoList)
        }

    }


}











































