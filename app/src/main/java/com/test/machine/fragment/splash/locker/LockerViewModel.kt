package com.test.machine.fragment.splash.locker

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.machine.base.BaseViewModel
import com.test.machine.pojo.Item
import com.test.machine.utils.readAndParseJSON
import com.test.machine.pojo.GroupedData
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LockerViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : BaseViewModel() {

    private var groupedDataList: List<GroupedData>?
    private var groupedItems: List<List<Item>>?

    private val _largestColumnSequence = MutableLiveData<Int>()
    val largestColumnSequence: LiveData<Int>
        get() = _largestColumnSequence

    private val _listGroupedData = MutableLiveData<List<GroupedData>?>()
    val listGroupedData: LiveData<List<GroupedData>?>
        get() = _listGroupedData

    init {
        val assetManager = context.resources.assets
        val fileName = "data.json" // Replace with your JSON file name
        val root = readAndParseJSON(assetManager, fileName)

        val items = root?.result?.items

        // Find the largest columnSequence
        _largestColumnSequence.value = items?.maxByOrNull {
            it.machineLocker.columnSequence
        }?.machineLocker?.columnSequence ?: -1

        // Group and sort the items based on columnSequence
        groupedItems = items
            ?.groupBy { it.machineLocker.columnSequence }
            ?.values
            ?.sortedBy { it.first().machineLocker.columnSequence }
            ?.map { group ->
                group.sortedBy { it.machineLocker.lockerSequenceNo }
            }


        // Create a list of grouped data
        groupedDataList = groupedItems?.map { group ->
            val columnSequence = group.first().machineLocker.columnSequence
            GroupedData(columnSequence, group)
        }
        _listGroupedData.value = groupedDataList
    }
}