package es.etg.dam.pmdmnko.gym


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import es.etg.dam.pmdmnko.gym.data.ClienteDatabase
import es.etg.dam.pmdmnko.gym.data.ClienteEntity
import es.etg.dam.pmdmnko.gym.data.ClienteTelefonosEntity
import es.etg.dam.pmdmnko.gym.data.TelefonoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SegundaActivity : AppCompatActivity() {

    companion object {
        lateinit var database: ClienteDatabase
        const val DATABASE_NAME = "cliente-db"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        // Inicializar la base de datos
        database = Room.databaseBuilder(
            this,
            ClienteDatabase::class.java,
            DATABASE_NAME
        ).build()

        // Obtener referencias a los elementos de la vista
        val txtName: TextView = findViewById(R.id.editTextText2)
        val txtEmail: TextView = findViewById(R.id.editTextTextEmailAddress2)
        val txtPassword: TextView = findViewById(R.id.editTextTextPassword2)
        val txtFecha: TextView = findViewById(R.id.editTextDate2)
        val boton: Button = findViewById(R.id.btnRegistrar)

        // Configurar el botón para guardar datos
        boton.setOnClickListener {
            val name = txtName.text.toString()
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()
            val date = txtFecha.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && date.isNotEmpty()) {
                guardarUsuario(name, email, password, date)
            } else {
                Toast.makeText(this, R.string.msgToast_segunda, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun guardarUsuario(name: String, email: String, password: String, date: String) {
        val clienteDao = database.clienteDao()
        val telefonoDao = database.telefonoDao()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Crear y guardar cliente
                val cliente = ClienteEntity(0, name, email, password, date)
                val clienteId = clienteDao.insert(cliente)

                // Crear y guardar teléfono asociado
                val telefono = TelefonoEntity(0, password, clienteId)
                telefonoDao.insert(telefono)

                // Mostrar mensaje y redirigir en el hilo principal
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SegundaActivity, getString(R.string.msgInsert), Toast.LENGTH_LONG).show()


                    val intent = Intent(this@SegundaActivity, MainActivity::class.java)
                    startActivity(intent)

                    finish()
                }
            } catch (e: Exception) {
                // Manejo de errores en el hilo principal
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SegundaActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}

