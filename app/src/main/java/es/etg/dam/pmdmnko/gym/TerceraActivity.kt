package es.etg.dam.pmdmnko.gym

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class TerceraActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tercera)

        val txtViewWelcome:TextView = findViewById(R.id.textWelcome)
        val extras = intent.extras
        val stringName = extras!!.getString("userName")
        val stringWelcome = getString(R.string.msg_welcome) +" " +  stringName + getString(R.string.exclamacion)
        txtViewWelcome.setText(stringWelcome)
    }
}