package es.etg.dam.pmdmnko.gym.data

import androidx.room.Embedded
import androidx.room.Relation

data class ClienteTelefonosEntity(
    @Embedded val cliente: ClienteEntity,
    @Relation(
        parentColumn = "id", //Entidad cliente
        entityColumn = "cliente" // Entidad secundaria: telefono

    )
    val telefonos: List<TelefonoEntity>
)
