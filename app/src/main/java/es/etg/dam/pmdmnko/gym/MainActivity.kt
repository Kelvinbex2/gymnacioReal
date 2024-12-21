package es.etg.dam.pmdmnko.gym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.etg.dam.pmdmnko.gym.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val boton: Button = binding.btnRegis
        val btn2: Button = binding.btnSign
        val txtEmail: TextView = binding.txtUser

        val nombre = leer()
        if(nombre !=null){
            val txtNombre = binding.txtUser.setText(nombre);
        }

        boton.setOnClickListener {
            val intent = Intent(this, SegundaActivity::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val stringUserName = txtEmail.text.toString()
            guardar()
            if (stringUserName.isNotEmpty()) {
                val intent = Intent(this, TerceraActivity::class.java)
                intent.putExtra("userName", stringUserName)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.msg_toast, Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun guardar(){
        val nombre = binding.txtUser.text
        val sharedPref = getPreferences(MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("nombre", nombre.toString())
        editor.apply()
    }


    fun leer(): String? {
        val sharedPref = getPreferences(MODE_PRIVATE)
        val nombre = sharedPref.getString("nombre", "")
        return nombre
    }

}
