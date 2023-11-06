package com.test.machine.pojo

data class Rows(val rows: List<Row>)

data class Row(
    val columns: List<Column>
)

data class Column(
    val type: String,
    val content: String
)
