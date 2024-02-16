package kr.co.lion.android01.newmemoproject_seonguk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.lion.android01.newmemoproject_seonguk.databinding.FragmentMainBinding
import kr.co.lion.android01.newmemoproject_seonguk.databinding.RowMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        return inflater.inflate(R.layout.fragment_main, container, false)
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

    }
    //어댑터 클래스
    inner class ReCyclerViewAdapter:RecyclerView.Adapter<ReCyclerViewAdapter.ViewHolderClass>(){

        //viewHolderClass
        inner class ViewHolderClass(rowMainBinding: RowMainBinding):RecyclerView.ViewHolder(rowMainBinding.root){
            var rowMainBinding:RowMainBinding
            init {
                this.rowMainBinding = rowMainBinding
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            var rowMainBinding = RowMainBinding.inflate(layoutInflater)
            var viewHolder = ViewHolderClass(rowMainBinding)
            return viewHolder
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowMainBinding.resultTextView.text = "제목"
            holder.rowMainBinding.resultImage.setImageResource(R.drawable.star_gh)
        }
    }


}











































