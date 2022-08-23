package com.maic.kurlyhack.feature

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.maic.kurlyhack.data.remote.KurlyClient
import com.maic.kurlyhack.databinding.ActivityLoginBinding
import com.maic.kurlyhack.util.callback
import com.maic.kurlyhack.util.showDrawer

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        activeBtn()
        clickBtnListener()
        //MyFirebaseMessagingService().showToken(binding.etUserNumber)

        /** FCM설정, Token값 가져오기 */
        MyFirebaseMessagingService().getFirebaseToken(binding.etUserNumber)
        // MyFirebaseMessagingService().getFirebaseToken()
        val clipboard: ClipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", binding.etUserNumber.text)
        clipboard.setPrimaryClip(clip)

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
                //binding.tvMainTitle.text = dataStr
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
            initNetwork()
        }

        binding.ivMainMenu.setOnClickListener {
            showDrawer(binding.drawerLayout, binding.navView)
        }
    }

    private fun initNetwork() {
        KurlyClient.userService.getWorkerId(
            binding.etUserNumber.text.toString().toInt()
        ).callback.onSuccess {
            if (it.data != null) {
                //MyFirebaseMessagingService().getFirebaseToken(it.data.workerId)
                val myWork: String
                val myPart: String
                val isPick: Boolean
                if (it.data.role == "PICK") {
                    myWork = "피킹 "
                    isPick = true
                } else {
                    myWork = "다스 "
                    isPick = false
                }
                if (it.data.passage != null) {
                    myPart = "통로 " + it.data.passage
                } else {
                    myPart = "구역 " + it.data.area
                }
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("workerId", it.data.workerId)
                intent.putExtra("workerPart", myWork + myPart)
                intent.putExtra("isPick", isPick)
                intent.putExtra("centerId", it.data.centerId)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }.enqueue()
    }
}
