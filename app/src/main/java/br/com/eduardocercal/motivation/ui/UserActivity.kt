package br.com.eduardocercal.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.eduardocercal.motivation.R
import br.com.eduardocercal.motivation.databinding.ActivityUserBinding
import br.com.eduardocercal.motivation.infra.MotivationConstants
import br.com.eduardocercal.motivation.infra.SecurityPreferences

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()

        verifyNameExists()
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_save -> handleSave()
        }
    }
    
    private fun verifyNameExists() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != "") {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()

        if (name == "") {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
            return
        }

        SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}