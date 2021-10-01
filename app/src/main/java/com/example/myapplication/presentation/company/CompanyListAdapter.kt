import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemCompanyBinding
import com.example.myapplication.domain.CompanyInformation

class CompanyListAdapter : RecyclerView.Adapter<CompanyListAdapter.ViewHolder>() {
    var mData: List<CompanyInformation> = emptyList()

    var onItemClickListener: ((CompanyInformation) -> Unit)? = null
    var onFavoriteClickListener: ((CompanyInformation) -> Unit)? = null

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

        fun bind(item: CompanyInformation) {
            Glide.with(binding.root)
                .load(item.thumbNail)
                .transition(withCrossFade())
                .into(binding.ivCompanyThumb)

            binding.tvCompanyTitle.text = item.companyName
            binding.tvCompanyIntro.text = item.shortIntro

            if (adapterPosition % 2 == 0) {
                binding.root.setBackgroundResource(R.color.light_grey)
            } else {
                binding.root.setBackgroundResource(R.color.white)
            }
        }
    }
}