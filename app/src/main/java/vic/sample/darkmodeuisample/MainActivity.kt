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
import vic.sample.darkmodeuisample.databinding.ActivityMainBinding

class MainActivity : BasicActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mHomeListItem = ArrayList<ListItem>()
    private val mHomeListAdapter: HomeListAdapter by lazy {
        HomeListAdapter(mHomeListItem) { view, position ->
            mHomeListItem[position].isBadgeShow = false
            mHomeListAdapter.notifyItemChanged(position)
            callToast("List Item Click, Position : $position", true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setViewListener()
    }

    private fun initView() {
        /*
        * To simulate loading data
        * */
        GlobalScope.launch(Main) {
            binding.homeLayLoadingArea.visibility = View.VISIBLE
            delay(2000)
            initListView()
            binding.homeLayLoadingArea.visibility = View.GONE
        }
    }

    private fun setViewListener() {
        binding.homeLayBtnSetting.setOnClickListener { callToast("Setting Click", true) }
        binding.homeTxtBtnMore.setOnClickListener { callToast("More Click", true) }
        binding.homeLayBtnPromo.setOnClickListener { callToast("Promo Click", true) }
        binding.homeLayBtnCart.setOnClickListener { callToast("Cart Click", true) }
        binding.homeLayBtnStore.setOnClickListener { callToast("Store Click", true) }
    }

    /*
    * RecycleView Init
    * */
    private fun initListView() {
        binding.homeRecycleView.apply {
            adapter = mHomeListAdapter
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