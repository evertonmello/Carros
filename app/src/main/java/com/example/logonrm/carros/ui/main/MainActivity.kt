package com.example.logonrm.carros.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDialogFragment
import android.widget.Toast
import com.example.logonrm.carros.R
import com.example.logonrm.carros.ui.listacarros.ListaCarrosFragment
import com.example.logonrm.carros.ui.novocarro.NovoCarroFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_lista -> {
                changeFragment(ListaCarrosFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_novo-> {
                changeFragment(NovoCarroFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_sobre-> {
                Toast.makeText(this, "Em construcao", Toast.LENGTH_LONG).show()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun changeFragment(fragment: Fragment){
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.containerFrag, fragment)
        transition.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
