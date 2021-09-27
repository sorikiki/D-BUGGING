import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.presentation.company.CompanyListItem
import java.util.ArrayList

class CompanyListAdapter internal constructor(list: ArrayList<CompanyListItem>?) :
    RecyclerView.Adapter<CompanyListAdapter.ViewHolder>() {
    private var mData: ArrayList<CompanyListItem>? = null

    // 클릭 이벤트 리스너 인터페이스 정의
    interface OnItemClickListener {
        fun onItemClick(v: View?, pos: Int)
    }

    private var mListener: OnItemClickListener? = null //전달된 리스너 객체를 저장할 변수

    //리스터 객체를 전달하는 메소드
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_company_list, parent, false)
        return ViewHolder(view)
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData!![position]

        //썸네일
        //val drawable =
            //holder.itemView.context.getDrawable(R.drawable.ic_fo_search_empty)
        //holder.thumb.background = drawable
        //  이름, 인트로
        holder.name.setText(item.getName())
        holder.intro.setText(item.getIntro())

    }

    // getItemCount() - 전체 데이터 갯수 리턴.
    override fun getItemCount(): Int {
        return mData!!.size
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var thumb: ImageView
        var name: TextView
        var intro: TextView

        init {
            // 뷰 객체에 대한 참조. (hold strong reference)
            thumb = itemView.findViewById(R.id.iv_company_thumb)
            name = itemView.findViewById(R.id.tv_company_title)
            intro = itemView.findViewById(R.id.tv_company_intro)

            //아이템 클릭이벤트처리
            itemView.setOnClickListener { v ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    if (mListener != null) {
                        mListener!!.onItemClick(v, position)
                    }
                }
            }

        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음.
    init {
        mData = list
    }

}