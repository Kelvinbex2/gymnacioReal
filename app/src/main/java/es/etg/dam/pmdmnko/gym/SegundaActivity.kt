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
import es.etg.dam.pmdmnko.gym.databinding.ActivityMainBinding
import es.etg.dam.pmdmnko.gym.databinding.ActivitySegundaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SegundaActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaBinding

    companion object {
        lateinit var database: ClienteDatabase
        const val DATABASE_NAME = "cliente-db"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        database = Room.databaseBuilder(
            this,
            ClienteDatabase::class.java,
            DATABASE_NAME
        ).build()


        val txtName: TextView = binding.editTextText2
        val txtEmail: TextView = binding.editTextTextEmailAddress2
        val txtPassword: TextView = binding.editTextTextPassword2
        val txtFecha: TextView = binding.editTextDate2
        val boton: Button = binding.btnRegistrar


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

                val cliente = ClienteEntity(0, name, email, password, date)
                val clienteId = clienteDao.insert(cliente)

                val emails = email.split(",")


                for (correo in emails) {
                    val telefono = TelefonoEntity(0, correo.trim(), clienteId) // Trim eliminará espacios adicionales
                    telefonoDao.insert(telefono)
                }
                val lista: List<ClienteTelefonosEntity> = clienteDao.getClientesTelefonos()

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SegundaActivity, getString(R.string.msgInsert), Toast.LENGTH_LONG).show()

                    val intent = Intent(this@SegundaActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SegundaActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }



}

