import androidx.room.Embedded
import androidx.room.Relation

data class UsuarioTelefonosEntity(
    @Embedded val cliente: UsuarioEntity,
    @Relation(
        parentColumn = "id", //Entidad cliente
        entityColumn = "usuario" // Entidad secundaria: telefono

    )
    val telefonos: List<TelefonoEntity>

)