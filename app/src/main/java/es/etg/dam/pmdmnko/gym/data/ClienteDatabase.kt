package es.etg.dam.pmdmnko.gym.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ClienteEntity::class, TelefonoEntity::class], version = 1)
abstract class ClienteDatabase: RoomDatabase() {

    abstract fun clienteDao(): ClienteDao

    abstract fun telefonoDao(): TelefonoDao
}