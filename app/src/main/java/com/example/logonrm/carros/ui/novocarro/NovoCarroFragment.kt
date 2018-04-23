package com.example.logonrm.carros.ui.novocarro


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.logonrm.carros.R
import com.example.logonrm.carros.api.CarroApi
import com.example.logonrm.carros.api.RetrofitClient
import com.example.logonrm.carros.model.Carro
import com.example.logonrm.carros.ui.listacarros.ListaCarrosFragment
import com.example.logonrm.carros.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger


/**
 * A simple [Fragment] subclass.
 */
class NovoCarroFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment
            return inflater!!.inflate(R.layout.fragment_novo_carro, container, false)
        }

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            btSalvar.setOnClickListener{

                var ano = if(inputAno.editText?.text.toString() == "") 0 else inputAno.editText?.text.toString().toInt()
                var marca = inputMarca.editText?.text.toString()
                var img = inputImg.editText?.text.toString()
                var modelo = inputModelo.editText?.text.toString()

                val carro = Carro(null,marca,modelo,ano,img)
                val api = RetrofitClient
                    .getInstance()
                    .create(CarroApi::class.java)

           api.salvar(carro)
                    .enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if(response?.isSuccessful == true){
                                Log.d("cfggfff",response.toString())

                                Toast.makeText(context,"Carro Salvo com Sucesso",
                                        Toast.LENGTH_LONG).show()
                            }else{
                                Toast.makeText(context,"Falha",
                                        Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Log.e("Carro", t?.message)
                        }
                    })
        }
    }

}// Required empty public constructor
