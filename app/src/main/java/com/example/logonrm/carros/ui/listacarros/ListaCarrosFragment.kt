package com.example.logonrm.carros.ui.listacarros


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.logonrm.carros.R
import com.example.logonrm.carros.api.CarroApi
import com.example.logonrm.carros.api.RetrofitClient
import com.example.logonrm.carros.model.Carro
import kotlinx.android.synthetic.main.erro.*
import kotlinx.android.synthetic.main.fragment_lista_carros.*
import kotlinx.android.synthetic.main.loading.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.security.auth.callback.CallbackHandler


/**
 * A simple [Fragment] subclass.
 */
class ListaCarrosFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_lista_carros, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carregarDados()
    }

    fun carregarDados(){
        val api = RetrofitClient.getInstance("https://carroapi-evertonmello.herokuapp.com")
                .create(CarroApi::class.java)

        loading.visibility = View.VISIBLE
        api.buscarTodos().enqueue(object: Callback<List<Carro>>{

            override fun onFailure(call: Call<List<Carro>>?, t: Throwable?) {
                containerErro.visibility = View.VISIBLE
                tvMsgErro.text = t?.message
                loading.visibility = View.GONE

            }

            override fun onResponse(call: Call<List<Carro>>?, response: Response<List<Carro>>?) {
                loading.visibility = View.GONE
                tvMsgErro.text = ""
                if(response?.isSuccessful == true){
                    setupLista(response?.body())
                }else{
                    containerErro.visibility = View.VISIBLE
                    tvMsgErro.text = response?.errorBody()?.charStream()?.readText()
                }
            }

        })
    }

    fun setupLista(carros: List<Carro>?) {
        carros.let {
            rvCarros.adapter = ListaCarrosAdapter(carros!!, context)
            val layoutManager = LinearLayoutManager(context)
            rvCarros.layoutManager = layoutManager
        }

    }

}// Required empty public constructor
