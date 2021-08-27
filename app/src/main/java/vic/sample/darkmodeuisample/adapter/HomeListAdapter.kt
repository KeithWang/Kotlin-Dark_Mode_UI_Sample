package vic.sample.darkmodeuisample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_home_list_item.view.*
import vic.sample.darkmodeuisample.R
import java.text.DecimalFormat


class HomeListAdapter(
    context: Context,
    private val items: ArrayList<ListItem>,
    private val onClickListener: ((view: View, position: Int) -> Unit)
) : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mNumberFormatter = DecimalFormat("#,###,###")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = mInflater.inflate(R.layout.row_home_list_item, parent, false)

        return ViewHolder(view, onClickListener)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position, mNumberFormatter)

    }

    class ViewHolder(
        private val view: View,
        private val onClickListener: ((view: View, position: Int) -> Unit)
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: ListItem, position: Int, DecimalFormat: DecimalFormat) {
            view.list_item_txt_coin_number.text = DecimalFormat.format(item.coinValue)
            view.list_item_processbar.progress = item.progressPercent
            view.list_item_txt_progress_value.text = "${item.progressPercent}%"
            view.list_item_txt_rewords_value.text = DecimalFormat.format(item.rewords)
            view.list_item_txt_sell_value.text = DecimalFormat.format(item.sell)
            view.list_item_txt_badge.visibility =
                if (item.isBadgeShow) View.VISIBLE else View.INVISIBLE

            view.list_item_lay_out.setOnClickListener {
                onClickListener.invoke(view, position)
            }

        }
    }
}
