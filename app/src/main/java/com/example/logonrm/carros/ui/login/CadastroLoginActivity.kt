package com.example.logonrm.carros.ui.login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.logonrm.carros.R
import com.example.logonrm.carros.api.RetrofitClient
import com.example.logonrm.carros.api.UsuarioApi
import com.example.logonrm.carros.model.Usuario
import com.example.logonrm.carros.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_cadastro_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_login)

        val api = RetrofitClient.getInstance()
                .create(UsuarioApi::class.java)

        button3.setOnClickListener({
            var login = cadLogTx?.text.toString()
            var senha = cadSenhatx?.text.toString()

            val usuario = Usuario(login, senha)
            val contexto = applicationContext
            val api = RetrofitClient.getInstance()
                    .create(UsuarioApi::class.java)

            val intent = Intent(applicationContext, LoginActivity::class.java)

            api.salvar(usuario)
                    .enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if(response?.isSuccessful == true){
                                startActivity(intent)
                            }else{
                                Toast.makeText(contexto, "Erro ao cadastrar usuario", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Log.e("Carro", t?.message)
                        }
                    })

        })

    }
}
