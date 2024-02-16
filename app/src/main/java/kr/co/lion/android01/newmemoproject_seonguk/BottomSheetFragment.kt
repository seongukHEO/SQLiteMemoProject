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
        initView()
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
                val memoIdx = arguments?.getInt("mainidx")!!
                //RecyclerView를 갱신한다 (함수사용)
                MemoDAO.deleteMemo(mainActivity, memoIdx)
                //RecyclerView갱신
                mainActivity.reloadRecyclerView()
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
                val memoIdx = arguments?.getInt("mainidx")!!
                val memoModal = MemoDAO.selctOneMemo(mainActivity, memoIdx)
                var bundle = Bundle()
                bundle.putInt("idx", memoModal.idx)
                //null을 넘겨주진 않지만 우선 널
                mainActivity.replaceFragment(FragmentName.MODIFY_FRAGMENT, true, true, bundle)
                dismiss()
            }
        }
    }
    //값을 보이게한다
    fun initView(){
        val memoIdx = arguments?.getInt("mainidx")!!
        //memo idx를 가지고 있는 데이터 1개를 가져온다
        val memoModal = MemoDAO.selctOneMemo(mainActivity, memoIdx)

        fragmentBottomSheetBinding.apply {
            titleTextView.text = memoModal.title
            contentsTextView.text = memoModal.contents
        }
    }
}

























