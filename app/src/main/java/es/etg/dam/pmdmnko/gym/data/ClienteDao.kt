package es.etg.dam.pmdmnko.gym.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ClienteDao {

    @Transaction
    @Query("SELECT * FROM cliente")
    suspend fun getClientesTelefonos(): List<ClienteTelefonosEntity>


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: ClienteEntity):Long

}