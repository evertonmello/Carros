package com.example.logonrm.carros.api

import com.example.logonrm.carros.model.Carro
import com.example.logonrm.carros.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsuarioApi {

    @GET("/login")
    fun buscarTodos(): Call<List<Usuario>>

    @POST("/login")
    fun salvar(@Body usuario: Usuario): Call<Void>
}