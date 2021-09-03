package vic.sample.darkmodeuisample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_home_list_item.view.*
import vic.sample.darkmodeuisample.R
import vic.sample.darkmodeuisample.databinding.ActivityMainBinding
import vic.sample.darkmodeuisample.databinding.RowHomeListItemBinding
import java.text.DecimalFormat


class HomeListAdapter(
    private val items: ArrayList<ListItem>,
    private val onClickListener: ((view: View, position: Int) -> Unit)
) : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    private lateinit var binding: RowHomeListItemBinding

    private var mNumberFormatter = DecimalFormat("#,###,###")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RowHomeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, onClickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position, mNumberFormatter)

    }

    class ViewHolder(
        private val binding: RowHomeListItemBinding,
        private val onClickListener: ((view: View, position: Int) -> Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem, position: Int, DecimalFormat: DecimalFormat) {
            binding.listItemTxtCoinNumber.text = DecimalFormat.format(item.coinValue)
            binding.listItemProcessbar.progress = item.progressPercent
            binding.listItemTxtProgressValue.text = "${item.progressPercent}%"
            binding.listItemTxtRewordsValue.text = DecimalFormat.format(item.rewords)
            binding.listItemTxtSellValue.text = DecimalFormat.format(item.sell)
            binding.listItemTxtBadge.visibility =
                if (item.isBadgeShow) View.VISIBLE else View.INVISIBLE

            binding.listItemLayOut.setOnClickListener {
                onClickListener.invoke(binding.root, position)
            }

        }
    }
}
