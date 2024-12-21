package es.etg.dam.pmdmnko.gym.data.dao

import UsuarioEntity
import UsuarioTelefonosEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UsuarioDao {
    @Transaction
    @Query("SELECT * FROM usuario")
    suspend fun getClientesTelefonos(): List<UsuarioTelefonosEntity>


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: UsuarioEntity):Long
}