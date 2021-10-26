package com.example.workchains

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workchains.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val btnSingleChainSucceed = binding.btnSingleChainSucceed
        val btnSingleChainFail = binding.btnSingleChainFail
        val btnGroupChainSucceed = binding.btnGroupChainSucceed
        val btnGroupChainFail = binding.btnGroupChainFail
        val btnMultipleChainSucceed = binding.btnMultipleChainSucceed
        val btnMultipleChainFail = binding.btnMultipleChainFail
    }
}