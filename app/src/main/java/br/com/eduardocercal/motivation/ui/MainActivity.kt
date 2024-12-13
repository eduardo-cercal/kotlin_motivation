package br.com.eduardocercal.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.eduardocercal.motivation.R
import br.com.eduardocercal.motivation.databinding.ActivityMainBinding
import br.com.eduardocercal.motivation.infra.MotivationConstants
import br.com.eduardocercal.motivation.infra.SecurityPreferences
import com.devmasterteam.motivation.repository.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.CATEGORY.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.hide()

        handleUserName()
        handleFilter(R.id.image_all_inclusive)
        handleNewPhrase()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAllInclusive.setOnClickListener(this)
        binding.imageEmotions.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)

    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.image_sunny, R.id.image_emotions, R.id.image_all_inclusive -> handleFilter(view.id)
            R.id.button_new_phrase -> handleNewPhrase()
        }
    }

    private fun handleFilter(id: Int) {
        binding.imageAllInclusive.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageEmotions.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all_inclusive -> {
                binding.imageAllInclusive.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )

                categoryId = MotivationConstants.CATEGORY.ALL
            }

            R.id.image_emotions -> {
                binding.imageEmotions.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.CATEGORY.EMOTIONS
            }

            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                categoryId = MotivationConstants.CATEGORY.SUNNY
            }
        }
    }

    private fun handleNewPhrase() {
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textHelloUser.text = "${getString(R.string.hello)}, $name!"
    }
}