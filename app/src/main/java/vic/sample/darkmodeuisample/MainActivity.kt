package vic.sample.darkmodeuisample

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import vic.sample.darkmodeuisample.adapter.HomeListAdapter
import vic.sample.darkmodeuisample.adapter.ListItem
import vic.sample.darkmodeuisample.basic.BasicActivity

class MainActivity : BasicActivity() {

    private val wLayLoadingArea : FrameLayout by lazy { home_lay_loading_area }
    private val wLayBtnSetting: MaterialCardView by lazy { home_lay_btn_setting }
    private val wTxtBtnMore: TextView by lazy { home_txt_btn_more }
    private val wLayBtnPromo: LinearLayout by lazy { home_lay_btn_promo }
    private val wLayBtnCart: LinearLayout by lazy { home_lay_btn_cart }
    private val wLayBtnStore: LinearLayout by lazy { home_lay_btn_store }

    private val wRecyclerView: RecyclerView by lazy { home_recycle_view }

    private val mHomeListItem = ArrayList<ListItem>()
    private val mHomeListAdapter: HomeListAdapter by lazy {
        HomeListAdapter(mContext, mHomeListItem) { view, position ->
            mHomeListItem[position].isBadgeShow = false
            mHomeListAdapter.notifyItemChanged(position)
            callToast("List Item Click, Position : $position", true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        setViewListener()
    }

    private fun initView() {
        /*
        * To simulate loading data
        * */
        GlobalScope.launch(Main) {
            wLayLoadingArea.visibility = View.VISIBLE
            delay(2000)
            initListView()
            wLayLoadingArea.visibility = View.GONE
        }
    }

    private fun setViewListener() {
        wLayBtnSetting.setOnClickListener { callToast("Setting Click", true) }
        wTxtBtnMore.setOnClickListener { callToast("More Click", true) }
        wLayBtnPromo.setOnClickListener { callToast("Promo Click", true) }
        wLayBtnCart.setOnClickListener { callToast("Cart Click", true) }
        wLayBtnStore.setOnClickListener { callToast("Store Click", true) }
    }

    /*
    * RecycleView Init
    * */
    private fun initListView() {
        wRecyclerView.apply {
            adapter = mHomeListAdapter
            layoutManager = LinearLayoutManager(
                this@MainActivity, RecyclerView.VERTICAL, false
            )
            itemAnimator = DefaultItemAnimator()
        }

        /*
        * Fake List Data
        * */
        for (i in 0 until (10..20).random()) {
            mHomeListItem.add(
                ListItem(
                    coinValue = (100..9999).random(),
                    progressPercent = (1..100).random(),
                    rewords = (100..9999).random(),
                    sell = (100..9999).random(),
                    isBadgeShow = ((1..9999).random() % 2 == 0),
                )
            )
        }
    }

}