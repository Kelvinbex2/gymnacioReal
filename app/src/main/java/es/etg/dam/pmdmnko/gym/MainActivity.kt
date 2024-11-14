package es.etg.dam.pmdmnko.gym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val boton: Button = findViewById(R.id.btnRegis)
        val btn2: Button = findViewById(R.id.btnSign)
        val txtEmail: TextView = findViewById(R.id.txtUser)

        boton.setOnClickListener {
            val intent = Intent(this, SegundaActivity::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            val stringUserName = txtEmail.text.toString()
            if (stringUserName.isNotEmpty()) {
                val intent = Intent(this, TerceraActivity::class.java)
                intent.putExtra("userName", stringUserName)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.msg_toast, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
