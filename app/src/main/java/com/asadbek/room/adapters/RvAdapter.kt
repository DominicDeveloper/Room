package com.asadbek.room.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asadbek.room.R
import com.asadbek.room.databinding.ItemRvBinding
import com.asadbek.room.models.AppDataBase
import com.asadbek.room.models.MyContact

class RvAdapter(val context: Context, val list:ArrayList<MyContact>,val on: onRvClick) : RecyclerView.Adapter<RvAdapter.VH>() {
            lateinit var appDataBase: AppDataBase
            inner class VH(var itemRv: View): RecyclerView.ViewHolder(itemRv){
                @SuppressLint("SetTextI18n")
                fun onBind(myContact: MyContact){
                    appDataBase = AppDataBase.getInstance(context)
                    itemRv.findViewById<TextView>(R.id.itemTxtName).text = myContact.name
                    itemRv.findViewById<TextView>(R.id.itemTxtNumber).text = myContact.number

                    itemRv.setOnLongClickListener {
                        appDataBase.myDao().deleteContact(myContact)
                        list.remove(myContact)
                        notifyDataSetChanged()
                        true
                    }

                    itemRv.setOnClickListener {
                        on.onClck(myContact)
                        notifyDataSetChanged()
                    }


                }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
                return  VH(LayoutInflater.from(parent.context).inflate(R.layout.item_rv,parent,false))
            }

            override fun onBindViewHolder(holder: VH, position: Int) {
                holder.onBind(list[position])
            }

            override fun getItemCount(): Int {
                return  list.size
            }

            interface  onRvClick{
                fun onClck(myContact: MyContact)
            }

}