package es.etg.dam.pmdmnko.gym

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SegundaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda)

        val txtName: TextView = findViewById(R.id.editTextText2)
        val boton: Button = findViewById(R.id.btnRegistrar)
        val txtEmail: TextView = findViewById(R.id.editTextTextEmailAddress2)


        val nameEmail = intent.extras?.getString("email")
        if (!nameEmail.isNullOrEmpty()) {
            txtEmail.text = nameEmail
        }

        boton.setOnClickListener {
            val stringName = txtName.text.toString()
            if (stringName.isNotEmpty()) {
                val intent = Intent(this, TerceraActivity::class.java)
                intent.putExtra("userName", stringName)
                startActivity(intent)
            } else {
                Toast.makeText(this, R.string.msgToast_segunda, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
