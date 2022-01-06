package com.dlocal.sampleapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dlocal.datacollector.DLCollector
import com.dlocal.datacollector.api.DLAdditionalData
import com.dlocal.sampleapp.databinding.ActivitySampleBinding

class SampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startSessionButton.setOnClickListener { startSession() }
        binding.getSessionIdButton.setOnClickListener { getSessionId() }

        binding.javaSampleButton.setOnClickListener {
            startActivity(Intent(this, JavaSampleActivity::class.java))
            finish()
        }
    }

    private fun startSession() {
        val additionalData = DLAdditionalData(userReference = "USER_REFERENCE_ID")
        DLCollector.startSession(additionalData)
        binding.sessionIdText.text = getString(R.string.session_started)
    }

    private fun getSessionId() {
        val sessionId = DLCollector.getSessionId() ?: getString(R.string.no_session_id)
        binding.sessionIdText.text = sessionId
    }
}