package com.example.logonrm.carros.ui.novocarro


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
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
            Log.e("Q", "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
            val api = RetrofitClient
                    .getInstance()
                    .create(CarroApi::class.java)
            val carro = Carro(null,inputAno.editText?.text.toString(),
                                inputMarca.editText?.text.toString(),
                                inputAno.editText?.text.toString().toInt(),"")

            api.salvar(carro)
                    .enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if(response?.isSuccessful == true){
                                Toast.makeText(context,"Sucesso",
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
