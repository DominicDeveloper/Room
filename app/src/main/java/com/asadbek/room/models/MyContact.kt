package com.asadbek.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity room da MyContact nomli table yaratib beradi
@Entity
class MyContact {

    /*
    annatatsiya tagida bitta o`zgaruvchi bo`lsa annatatsiyamiz aynan osha o`zgaruvchiga tasir qiladi
     */
    /*
      O`garuvchi tipini klass ga tenglab bo`lmaydi xatolik chiqarib beradi va bir jadval butun boshli klass malumotlarini qo`sha olmaydi

     */
   // @ColumnInfo("id")
    @PrimaryKey(autoGenerate = true) // avtomatik id lar oshib boradi
    var id:Int? = null

    var name:String? = null

    var number:String? = null

    constructor()
    constructor(id: Int?, name: String?, number: String?) {
        this.id = id
        this.name = name
        this.number = number
    }
}