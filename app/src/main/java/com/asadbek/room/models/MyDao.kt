package com.asadbek.room.models

import androidx.room.*

@Dao
interface MyDao {

    @Transaction
    @Query("select * from MyContact")
    fun getAllContact():List<MyContact>

    @Insert
    fun addContact(myContact: MyContact)

    @Delete(entity = MyContact::class)
    fun deleteContact(myContact: MyContact)

    @Update(entity = MyContact::class)
    fun editContact(myContact: MyContact)


}