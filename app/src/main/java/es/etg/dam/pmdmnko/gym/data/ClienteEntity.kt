package es.etg.dam.pmdmnko.gym.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cliente")
data class ClienteEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val nombre:String = "",
    val email:String = "",
    val password:String = "",
    val date:String = ""

)