package kr.co.lion.android01.newmemoproject_seonguk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kr.co.lion.android01.newmemoproject_seonguk.databinding.FragmentModifyBinding

class ModifyFragment : Fragment() {

    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity
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
            titlemodifyText.setText("허서우")
            textModifyContents.setText("아아아아아")
        }

    }

    //출력
    fun setEvent(){
        fragmentModifyBinding.apply {
            medifyButton.setOnClickListener {

                mainActivity.replaceFragment(FragmentName.MAIN_FRAGMENT, true, true, null)
            }
        }
    }
}


































