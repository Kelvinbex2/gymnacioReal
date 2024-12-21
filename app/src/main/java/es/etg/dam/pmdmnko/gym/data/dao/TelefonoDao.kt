package es.etg.dam.pmdmnko.gym.data.dao

import TelefonoEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface TelefonoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(telefono: TelefonoEntity)
}