package com.maic.kurlyhack.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.maic.kurlyhack.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activeBtn()
        clickBtnListener()
    }

    private fun activeBtn() {
        with(binding) {
            etUserNumber.addTextChangedListener {
                btnMainStart.isEnabled = etUserNumber.text.toString().isNotEmpty()
            }
        }
    }

    private fun clickBtnListener() {
        binding.btnMainStart.setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }
}
