package es.etg.dam.pmdmnko.gym

import TelefonoEntity
import UsuarioDatabase
import UsuarioEntity
import UsuarioTelefonosEntity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SegundaActivity : AppCompatActivity() {

    companion object {
        lateinit var database: UsuarioDatabase
        const val DATABASE_NAME = "cliente-db"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        SegundaActivity.database = Room.databaseBuilder(
            this,
            UsuarioDatabase::class.java,
            DATABASE_NAME
        ).build()

        val txtName: TextView = findViewById(R.id.editTextText2)
        val txtEmail: TextView = findViewById(R.id.editTextTextEmailAddress2)
        val txtPassword: TextView = findViewById(R.id.editTextTextPassword2)
        val txtFecha: TextView = findViewById(R.id.editTextDate2)
        val boton: Button = findViewById(R.id.btnRegistrar)

        boton.setOnClickListener {
            val name = txtName.text.toString()
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()
            val date = txtFecha.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && date.isNotEmpty()) {
                guardar(name, email, password,date)
                val intent = Intent(this, TerceraActivity::class.java)
                intent.putExtra(getString(R.string.claveSegunIntent), name)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.msgToast_segunda, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun guardar(name: String, email: String, password: String,date:String) {
        val usuarioDao = database.clienteDao()
        val telefonoDao = database.telefonoDao()

        CoroutineScope(Dispatchers.IO).launch {

            val usuario = UsuarioEntity(0, name, email,date)
            val usuarioId =usuarioDao.insert(usuario) // Guardar cliente y obtener ID

            // Crear y guardar teléfono asociado al cliente
            val telefono = TelefonoEntity(0, password, usuarioId)
            telefonoDao.insert(telefono)

            val lista: List<UsuarioTelefonosEntity> = usuarioDao.getClientesTelefonos()


                Toast.makeText(this@SegundaActivity, getString(R.string.msgInsert), Toast.LENGTH_LONG).show()

        }
    }




}
