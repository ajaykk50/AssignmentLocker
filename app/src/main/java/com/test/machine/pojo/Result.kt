package com.test.machine.pojo

import com.test.machine.pojo.Item

data class Result(
    val items: List<Item>,
    val totalCount: Int
)