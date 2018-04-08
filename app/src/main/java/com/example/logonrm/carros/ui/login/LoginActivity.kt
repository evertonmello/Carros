package com.example.logonrm.carros.ui.login
import android.content.Intent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.design.R.id.message
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.logonrm.carros.R
import com.example.logonrm.carros.api.CarroApi
import com.example.logonrm.carros.api.RetrofitClient
import com.example.logonrm.carros.api.UsuarioApi
import com.example.logonrm.carros.model.Carro
import com.example.logonrm.carros.model.Usuario
import com.example.logonrm.carros.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.erro.*
import kotlinx.android.synthetic.main.fragment_novo_carro.*
import kotlinx.android.synthetic.main.loading.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button.setOnClickListener({
            var login = etLogin?.text.toString()
            var senha = etSenha?.text.toString()

            val usuario = Usuario(login, senha)
            val contexto = applicationContext
            val api = RetrofitClient.getInstance()
                    .create(UsuarioApi::class.java)

            val intent = Intent(applicationContext, MainActivity::class.java)

            api.salvar(usuario)
                    .enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                            if(response?.isSuccessful == true){
                                startActivity(intent)
                            }else{
                                Toast.makeText(contexto, "Usuário inválido", Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<Void>?, t: Throwable?) {
                            Log.e("Carro", t?.message)
                        }
                    })












        })
    }


}
