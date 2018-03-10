package com.example.logonrm.carros.api

import com.example.logonrm.carros.model.Carro
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by logonrm on 10/03/2018.
 */
interface CarroApi{
    @GET("/carro")
    fun buscarTodos():Call<List<Carro>>

}