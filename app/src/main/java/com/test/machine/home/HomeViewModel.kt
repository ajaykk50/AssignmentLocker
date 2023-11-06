package com.test.machine.home

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.test.machine.adapter.CardAdapter
import com.test.machine.base.BaseViewModel
import com.test.machine.pojo.CardDetails
import com.test.machine.utils.SingleLiveEvent
import com.test.machine.R
import com.test.machine.utils.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider,
    @ApplicationContext private val context: Context,
) : BaseViewModel() {


    private val _cardDetailsResponse = MutableLiveData<List<CardDetails>>()
    val cardDetailsResponse: LiveData<List<CardDetails>>
        get() = _cardDetailsResponse


    private val _menuClicked = SingleLiveEvent<Boolean>()
    val menuClicked: SingleLiveEvent<Boolean>
        get() = _menuClicked

    private val _cardsClicked = SingleLiveEvent<Boolean>()
    val cardsClicked: SingleLiveEvent<Boolean>
        get() = _cardsClicked

    init {
        var cardsList: MutableList<CardDetails> = mutableListOf()
        cardsList.add(
            CardDetails(
                resourceProvider.getString(R.string.meachine_status),
                R.drawable.first_card,
                R.drawable.ic_status
            )
        )
        cardsList.add(
            CardDetails(
                resourceProvider.getString(R.string.pending_trans),
                R.drawable.second_card,
                R.drawable.ic_transactions
            )
        )
        cardsList.add(
            CardDetails(
                resourceProvider.getString(R.string.test_peripherals),
                R.drawable.thid_card,
                R.drawable.ic_testing
            )
        )

        _cardDetailsResponse.value = cardsList
    }

    fun getMenu() {
        _menuClicked.value = true
    }

    fun getLocker() {
        _cardsClicked.value = true
    }

    companion object {
        val sliderCurrentPage = MutableLiveData<Int?>(0)

        @JvmStatic
        @BindingAdapter("app:items")
        fun setViewPagerItems(viewPager: ViewPager2, data: List<CardDetails?>?) {
            val adapter = CardAdapter(viewPager.context, data)
            viewPager.adapter = adapter
            sliderCurrentPage.value = viewPager.currentItem
            setUpCardSlider(viewPager)
        }

        @JvmStatic
        @BindingAdapter("app:onPageChanged")
        fun ViewPager2.OnPageChangeCallback(viewPager: ViewPager2) {
            sliderCurrentPage.value = viewPager.currentItem
        }

        private fun setUpCardSlider(vpCard: ViewPager2) {
            vpCard.clipToPadding = false
            vpCard.clipChildren = false
            vpCard.offscreenPageLimit = 3
            vpCard.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compsitTransfom = CompositePageTransformer()
            compsitTransfom.addTransformer(MarginPageTransformer(30))
            compsitTransfom.addTransformer { page, position ->
            }
            vpCard.setPageTransformer(compsitTransfom)
            vpCard.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            })

        }
    }
}