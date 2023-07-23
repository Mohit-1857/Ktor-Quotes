package com.example.Table

import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object QuotesEntity : Table<Nothing>("quote") {
    val id = int("id").primaryKey()
    val quote = varchar("quote")
}