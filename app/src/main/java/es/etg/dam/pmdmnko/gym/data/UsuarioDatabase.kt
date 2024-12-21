import androidx.room.Database
import androidx.room.RoomDatabase
import es.etg.dam.pmdmnko.gym.data.dao.TelefonoDao
import es.etg.dam.pmdmnko.gym.data.dao.UsuarioDao

@Database(entities = [UsuarioEntity::class, TelefonoEntity::class], version = 1)
abstract class UsuarioDatabase: RoomDatabase() {

    abstract fun clienteDao(): UsuarioDao

    abstract fun telefonoDao(): TelefonoDao
}