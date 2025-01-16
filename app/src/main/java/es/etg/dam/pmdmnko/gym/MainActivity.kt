package es.etg.dam.pmdmnko.gym

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import es.etg.dam.pmdmnko.gym.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val READ_CONTACTS_REQUEST_CODE = 0
    }


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
            checkReadContactsPermission()
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

    private fun checkReadContactsPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR)
            != PackageManager.PERMISSION_GRANTED) {
            requestReadConctactsPermission()
        } else {
            Toast.makeText(this, getString(R.string.funcionalidad), Toast.LENGTH_SHORT).show()
        }
    }



    private fun requestReadConctactsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_CONTACTS)) {

            Toast.makeText(this,getString(R.string.MSG_CONCEDA), Toast.LENGTH_SHORT).show()
        } else {
            //
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_CONTACTS),
                Companion.READ_CONTACTS_REQUEST_CODE
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            READ_CONTACTS_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, getString(R.string.MSG_ESO), Toast.LENGTH_SHORT).show()
                } else {

                    Toast.makeText(this, getString(R.string.MSG_CONCEDA), Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {

            }
        }
    }

}
