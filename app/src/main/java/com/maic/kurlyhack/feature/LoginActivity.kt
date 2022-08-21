package com.maic.kurlyhack.feature

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.maic.kurlyhack.databinding.ActivityLoginBinding
import com.maic.kurlyhack.util.showDrawer

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        activeBtn()
        clickBtnListener()
        MyFirebaseMessagingService().getFirebaseToken()

        /** DynamicLink 수신확인 */
        initDynamicLink()

        setContentView(binding.root)
    }

    /** DynamicLink */
    private fun initDynamicLink() {
        val dynamicLinkData = intent.extras
        if (dynamicLinkData != null) {
            var dataStr = "DynamicLink 수신받은 값\n"
            for (key in dynamicLinkData.keySet()) {
                dataStr += "key: $key / value: ${dynamicLinkData.getString(key)}\n"
            }
        }
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

        binding.ivMainMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }
}
