import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemCompanyBinding
import com.example.myapplication.domain.Company

class CompanyListAdapter : RecyclerView.Adapter<CompanyListAdapter.ViewHolder>() {
    var mData: List<Company> = emptyList()

    var onItemClickListener: ((Company) -> Unit)? = null
    var onFavoriteClickListener: ((Company) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
     = ViewHolder(ItemCompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder(private val binding: ItemCompanyBinding): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(mData[adapterPosition])
            }

            binding.ibInterest.setOnClickListener {
                onFavoriteClickListener?.invoke(mData[adapterPosition])
            }
        }

        fun bind(item: Company) {
            val thumbImageView = binding.ivCompanyThumb
            val titleTextView = binding.tvCompanyTitle
            val introTextView = binding.tvCompanyIntro

            //todo item 의 속성을 각각의 뷰에 대입입
        }
    }
}