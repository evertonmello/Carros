package com.example.logonrm.carros.api

import com.example.logonrm.carros.model.Carro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

/**
 * Created by logonrm on 10/03/2018.
 */
interface CarroApi{
    @GET("/carro")
    fun buscarTodos():Call<List<Carro>>

    @GET("/carro/{modelo}")
    fun buscarCarro(@Path("modelo") modelo: String): Call<Carro>

    @POST("/carro")
    fun salvar(@Body carro: Carro): Call<Void>


}