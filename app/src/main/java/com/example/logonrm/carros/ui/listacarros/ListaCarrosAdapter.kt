package com.example.logonrm.carros.ui.listacarros

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.example.logonrm.carros.R
import com.example.logonrm.carros.model.Carro
import kotlinx.android.synthetic.main.item_carro.view.*

class ListaCarrosAdapter(private val carros: List<Carro>,
                         private val context: Context ):RecyclerView.Adapter<ListaCarrosAdapter.MeuViewHolder>(){

    override fun getItemCount(): Int {
       return carros.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MeuViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_carro, parent, false)
        return MeuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeuViewHolder?, position: Int) {
        val carro = carros[position]
        holder?.let {
            it.bindView(carro)
        }
    }

    class MeuViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView){
        fun bindView(carro: Carro){
            itemView.tvMarca.text = carro.marca
            itemView.tvModelo.text = carro.marca
        }
    }
}