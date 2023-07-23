package com.example.db

import org.ktorm.database.Database

object DatabaseConnection{

    val database = Database.connect(
        url = "jdbc:mysql://127.0.0.1:3306/quotes",
        driver = "com.mysql.cj.jdbc.Driver",
        user  = "root",
        password = "rooti9"
    )
}