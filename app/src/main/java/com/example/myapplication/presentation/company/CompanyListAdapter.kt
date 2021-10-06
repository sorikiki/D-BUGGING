import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemCompanyBinding
import com.example.myapplication.domain.CompanyInformation

class CompanyListAdapter : ListAdapter<CompanyInformation, CompanyListAdapter.ViewHolder>(diffUtil) {

    var onItemClickListener: ((CompanyInformation) -> Unit)? = null
    var onFavoriteClickListener: ((CompanyInformation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
     = ViewHolder(ItemCompanyBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemCompanyBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CompanyInformation) {

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(currentList[adapterPosition])
            }

            binding.ibInterest.setOnClickListener {
                onFavoriteClickListener?.invoke(currentList[adapterPosition])
            }

            Glide.with(binding.root)
                .load(item.thumbNail)
                .transition(withCrossFade())
                .into(binding.ivCompanyThumb)

            binding.tvCompanyTitle.text = item.companyName
            binding.tvCompanyIntro.text = item.shortIntro

            if (item.isCompanyInterested == true) {
                binding.ibInterest.setBackgroundResource(R.drawable.ic_heart_fill)
            } else {
                binding.ibInterest.setBackgroundResource(R.drawable.ic_heart_line)
            }

            if (adapterPosition % 2 == 0) {
                binding.root.setBackgroundResource(R.color.light_grey)
            } else {
                binding.root.setBackgroundResource(R.color.white)
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CompanyInformation>() {
            override fun areItemsTheSame(oldItem: CompanyInformation, newItem: CompanyInformation): Boolean {
                return oldItem.companyId == newItem.companyId
            }

            override fun areContentsTheSame(oldItem: CompanyInformation, newItem: CompanyInformation): Boolean {
                return oldItem == newItem
            }

        }
    }
}