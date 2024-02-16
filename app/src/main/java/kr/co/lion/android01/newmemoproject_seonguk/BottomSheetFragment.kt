package kr.co.lion.android01.newmemoproject_seonguk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kr.co.lion.android01.newmemoproject_seonguk.databinding.FragmentBottomSheetBinding


class BottomSheetFragment : BottomSheetDialogFragment() {

    lateinit var fragmentBottomSheetBinding: FragmentBottomSheetBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBottomSheetBinding = FragmentBottomSheetBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        setEvent()
        return fragmentBottomSheetBinding.root
    }

    //출력한다
    fun setEvent(){
        fragmentBottomSheetBinding.apply {
            titleTextView.text
            contentsTextView.text

            //버튼을 클릭했을때
            //1, 삭제 버튼
            deletebutton.setOnClickListener {
                //정보를 삭제해준다
                //RecyclerView를 갱신한다 (함수사용)
                //내려준다
                dismiss()

            }
            //2, 취소버튼
            cancelButton.setOnClickListener {
                //내려준다
                dismiss()
            }
            //3, 수정버튼
            modifyButton.setOnClickListener {
                //modify로 넘어간다
                //null을 넘겨주진 않지만 우선 널
                mainActivity.replaceFragment(FragmentName.MODIFY_FRAGMENT, true, true, null)
                dismiss()
            }
        }
    }
}

























