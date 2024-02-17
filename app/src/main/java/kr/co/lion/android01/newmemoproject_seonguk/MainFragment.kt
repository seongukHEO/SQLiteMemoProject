package kr.co.lion.android01.newmemoproject_seonguk

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.android01.newmemoproject_seonguk.databinding.FragmentMainBinding
import kr.co.lion.android01.newmemoproject_seonguk.databinding.RowMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    //정보를 담을 객체 생성
    lateinit var memoList:MutableList<Trip>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        saveData()
        setView()
        setEvent()
        setToolBar()
        //changeData()
        return fragmentMainBinding.root
    }

    override fun onResume() {
        super.onResume()
        fragmentMainBinding.recyclerview.adapter?.notifyDataSetChanged()
    }
    //툴바 설정
    fun setToolBar(){
        fragmentMainBinding.apply {
            materialToolbar.apply {
                //타이틀
                title = "AllMemo"
                //메뉴 설정
                inflateMenu(R.menu.main_menu)
                //메뉴를 클릭했을때?
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.filter_menu -> {

                        }
                    }

                    true
                }
            }
        }
    }
    fun saveData(){
        memoList = MemoDAO.selectAllMemo(mainActivity)

    }

    //floatButton클릭
    fun setEvent(){
        fragmentMainBinding.apply {
            floatingActionButton.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.INPUT_FRAGMENT, true, true, null)
            }
        }
    }

    //view설정
    fun setView(){
        fragmentMainBinding.apply {
            recyclerview.apply {
                adapter = ReCyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                //데코
                var deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
            }
        }

    }
//    fun changeData(){
//        var memo2 = arguments?.getInt("idx")
//        if (memo2 != null){
//            var memoModel = MemoDAO.selctOneMemo(mainActivity, memo2)
//            memoList.add(memoModel)
//            Log.e("test123", "${memoModel.title}")
//
//        }
//    }

    //어댑터 클래스
    inner class ReCyclerViewAdapter:RecyclerView.Adapter<ReCyclerViewAdapter.ViewHolderClass>(){

        //viewHolderClass
        inner class ViewHolderClass(rowMainBinding: RowMainBinding):RecyclerView.ViewHolder(rowMainBinding.root){
            var rowMainBinding:RowMainBinding
            init {
                this.rowMainBinding = rowMainBinding

                //가로 세로 길이 설정
                this.rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            var rowMainBinding = RowMainBinding.inflate(layoutInflater)
            var viewHolder = ViewHolderClass(rowMainBinding)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            var memo = memoList[position]
            Log.e("test123", "${memo.title}")
            holder.rowMainBinding.resultTextView.text = "제목 : ${memoList[position].title}"
            when(memo.important){
                0 -> holder.rowMainBinding.resultImage.setImageResource(R.drawable.star_hjhj)
            }
            //클릭했을떄
            holder.rowMainBinding.root.setOnClickListener {
                //객체를 생성해준다
                var bottomSheetFragment = BottomSheetFragment()

                //position번쨰 추출
                var sheetBundle = Bundle()
                sheetBundle.putInt("mainidx", memoList[position].idx)
                bottomSheetFragment.arguments = sheetBundle

                bottomSheetFragment.show(mainActivity.supportFragmentManager, "BottomSheet")
                //Log.e("test123", "${memo.title}")

            }
        }
    }
    fun reloadRecyclerView(){
        //데이터를 읽어온다
        memoList = MemoDAO.selectAllMemo(mainActivity)
        //RecyclerView를 갱신한다
        fragmentMainBinding.recyclerview.adapter?.notifyDataSetChanged()
    }





}












































