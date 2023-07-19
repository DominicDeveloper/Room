package com.asadbek.room.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// object olib bo`lmaydi abstarct klassdan
@Database(entities = [MyContact::class], version = 1) // MyContact nomli jadval yaratib beradi
abstract class AppDataBase:RoomDatabase(){
    abstract fun myDao():MyDao // qo`shimcha funksiyalarimizdan foydalanish qoshish ochirish

    companion object {
        private var instance:AppDataBase? = null

        @Synchronized
        fun getInstance(context: Context):AppDataBase{ // funksiya ishlaganda databseni yaratib ketadi.
            if (instance == null){ // baza quilmagan taqdirda bazani qurib beradi
                instance = Room.databaseBuilder(context,AppDataBase::class.java,"mycontact.db")
                    .fallbackToDestructiveMigration() // eski database ni ochirib yuborish
                    .allowMainThreadQueries() // asosiy oqimda ishlashga rozilik
                    .build()

            }
            return instance!!
        }
    }

}