package es.etg.dam.pmdmnko.gym.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "telefono")
data class TelefonoEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val numero:String,
    val cliente: Long = 0 //Éste será para la relación con el cliente
)
