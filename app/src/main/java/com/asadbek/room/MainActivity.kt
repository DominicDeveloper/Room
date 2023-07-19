package com.asadbek.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.asadbek.room.adapters.RvAdapter
import com.asadbek.room.databinding.ActivityMainBinding
import com.asadbek.room.models.AppDataBase
import com.asadbek.room.models.MyContact

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var appDataBase: AppDataBase // abstract klassligi sababli object olinmaydi togridan tori yozib ketishimiz mumkin
    lateinit var list: ArrayList<MyContact>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = ArrayList()

        appDataBase = AppDataBase.getInstance(this)

        list.addAll(appDataBase.myDao().getAllContact())
        val rvAdapter = RvAdapter(this,list,object :RvAdapter.onRvClick{
            override fun onClck(myContact: MyContact) {
                appDataBase.myDao().editContact(MyContact(myContact.id,"Asadbek","Azimov"))
                list.clear()
                list.addAll(appDataBase.myDao().getAllContact())

            }
        })


        binding.btnSave.setOnClickListener {
            val myContact = MyContact()
            myContact.name = binding.edtText.text.toString().trim()
            myContact.number = binding.edtNumber.text.toString().trim()
            
            appDataBase.myDao().addContact(myContact)
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
            list.clear()
            list.addAll(appDataBase.myDao().getAllContact())
            rvAdapter.notifyDataSetChanged()
            binding.edtText.text.clear()
            binding.edtNumber.text.clear()
        }



        binding.rv.adapter = rvAdapter

    }

}