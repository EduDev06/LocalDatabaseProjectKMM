package org.sample.preferencesapp.core.data.adapter

import app.cash.sqldelight.ColumnAdapter

val idAdapter = object : ColumnAdapter<Int, Long> {
    override fun decode(databaseValue: Long): Int {
        return databaseValue.toInt()
    }

    override fun encode(value: Int): Long {
        return value.toLong()
    }
}

val colorAdapter = object : ColumnAdapter<Long, Long> {
    override fun decode(databaseValue: Long): Long {
        return databaseValue
    }

    override fun encode(value: Long): Long {
        return value
    }
}
