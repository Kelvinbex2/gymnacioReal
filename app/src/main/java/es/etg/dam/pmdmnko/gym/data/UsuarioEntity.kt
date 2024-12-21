import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val nombre:String = "",
    val email:String = "",
    val fecha:String = ""
)