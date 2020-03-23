package com.vlogplusplus.videoEditor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.vlogplusplus.R

class OptiSplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opti_splash_screen)
        initListener()
    }

    private fun initListener() {
        val handler = Handler()
        handler.postDelayed({ callActivityIntent() }, 3000)
    }

    private fun callActivityIntent() {
        val intentFlag = Intent(this@OptiSplashScreen, OptiMainActivity::class.java)
        intentFlag.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intentFlag)
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}