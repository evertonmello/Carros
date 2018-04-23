package com.example.logonrm.carros.ui.buscarCarro

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.logonrm.carros.R
import com.example.logonrm.carros.api.CarroApi
import com.example.logonrm.carros.api.RetrofitClient
import com.example.logonrm.carros.model.Carro
import kotlinx.android.synthetic.main.activity_buscar.*
import kotlinx.android.synthetic.main.erro.*
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import kotlinx.android.synthetic.main.loading.*
import kotlinx.android.synthetic.main.sobre.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscarFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.activity_buscar, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button4.setOnClickListener({

            var modelo = buscarIpt.editableText.toString();
            Log.e("aaa","!!!!!!!!!!!!!!!!!!!!!!!" + modelo )



            val api = RetrofitClient.getInstance()
                    .create(CarroApi::class.java)

           // loading.visibility = View.VISIBLE

            api.buscarCarro(modelo).enqueue(object: Callback<Carro> {

                override fun onFailure(call: Call<Carro>?, t: Throwable?) {
                     //   containerErro.visibility = View.VISIBLE
                      //  tvMsgErro.text = t?.message
                      //  loading.visibility = View.GONE

                    Toast.makeText(context,"Erro! Modelo não cadastrado",
                            Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<Carro>?, response: Response<Carro>?) {
                      //  loading.visibility = View.GONE
                       // tvMsgErro.text = ""
                        if(response?.isSuccessful == true){
                            //   setupLista(response?.body())
                            var carro = response?.body();

                            marcaTv.text = carro?.marca.toString();
                            anoTv.text = carro?.ano.toString();
                            modeloTv.text = carro?.modelo.toString()
                            imgTv.text = carro?.urlImagem.toString()

                        }else{
                            Toast.makeText(context,"Erro!",
                                    Toast.LENGTH_LONG).show()
                            Log.e("erro",response?.errorBody()?.charStream()?.readText() )
                        }
                    }

                })

        })

    }


}