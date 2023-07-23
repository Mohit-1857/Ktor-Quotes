package com.example.Routes

import com.example.Model.Quote
import com.example.Model.QuoteRequest
import com.example.Model.QuoteResponse
import com.example.Table.QuotesEntity
import com.example.db.DatabaseConnection
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*

fun Application.route(){
    routing {

        val db = DatabaseConnection.database

        // Retrieve all the quotes
        get("/quotes"){
            var idQ = -1
            var quoteQ = ""
            val result = db.from(QuotesEntity).select()
                .map {
                        val id = it[QuotesEntity.id]
                        val quote = it[QuotesEntity.quote]
                        Quote(id ?: -1 , quote ?: "")
                }

            if(result != null){
                call.respond(result)
            }
        }

        // Store quotes to the database
        post("/quotes"){
            val request = call.receive<QuoteRequest>()

            val result = db.insert(QuotesEntity){
                set(it.quote , request.quote)
            }

            if(result == 1){
                call.respond(HttpStatusCode.OK , QuoteResponse(
                    status = true,
                    data = "Inserted Successfully"
                ))
            }
            else{
                call.respond(HttpStatusCode.BadRequest , QuoteResponse(
                    status = false,
                    data = "Failed to insert"
                ))
            }
        }

        // Update quotes
        put("/quotes"){
            val request = call.receive<Quote>()

            val result = db.update(QuotesEntity){
                set(it.quote , request.quote)
                where {
                    it.id eq request.id
                }
            }

            if(result == 1){
                call.respond(HttpStatusCode.OK , QuoteResponse(
                    status = true,
                    data = "Update Successfully"
                ))
            }
            else{
                call.respond(HttpStatusCode.BadRequest , QuoteResponse(
                    status = false,
                    data = "Failed to update"
                ))
            }
        }

        // Delete quotes
        delete("/quotes/{id}"){
            val id = call.parameters["id"]?.toInt() ?: -1

            val result = db.delete(QuotesEntity){
                it.id eq id
            }

            if(result == 1){
                call.respond(HttpStatusCode.OK , QuoteResponse(
                    status = true,
                    data = "Delete Successfully"
                ))
            }
            else{
                call.respond(HttpStatusCode.OK , QuoteResponse(
                    status = false,
                    data = "Failed to Delete"
                ))
            }
        }


    }
}