package es.etg.dam.pmdmnko.gym

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import es.etg.dam.pmdmnko.gym.databinding.ActivitySegundaBinding
import es.etg.dam.pmdmnko.gym.databinding.ActivityTerceraBinding

class TerceraActivity : AppCompatActivity() {
    private var derechaRojo = true
    private lateinit var binding: ActivityTerceraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerceraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<RedFragment>(binding.frgIzq.id)
                add<BlueFragment>(binding.frgCentre.id)
                add<WhiteFragment>(binding.fragDerec.id)
            }
        }
    }

    fun intercambiar(view: View) {
        val fragmentIzq = binding.frgIzq.getFragment<Fragment>()

        supportFragmentManager.commit {
            setReorderingAllowed(true)

            if (fragmentIzq is RedFragment) {
                replace<RedFragment>(binding.frgCentre.id)
                replace<BlueFragment>(binding.fragDerec.id)
                replace<WhiteFragment>(binding.frgIzq.id)
            } else if (fragmentIzq is BlueFragment) {
                replace<BlueFragment>(binding.frgCentre.id)
                replace<WhiteFragment>(binding.fragDerec.id)
                replace<RedFragment>(binding.frgIzq.id)
            } else if (fragmentIzq is WhiteFragment) {
                replace<WhiteFragment>(binding.frgCentre.id)
                replace<RedFragment>(binding.fragDerec.id)
                replace<BlueFragment>(binding.frgIzq.id)
            }
        }
    }
}
