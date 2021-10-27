package com.example.workchains

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkContinuation
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.workchains.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val workManager = WorkManager.getInstance(this)

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

        btnSingleChainSucceed.setOnClickListener {
            val objectDetectionWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val networkRequestWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val databaseWriterWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(objectDetectionWorkerRequest)
                .then(networkRequestWorkerRequest)
                .then(databaseWriterWorkerRequest)
                .enqueue()
        }

        btnSingleChainFail.setOnClickListener {
            val objectDetectionWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val networkRequestWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to false))
                .build()
            val databaseWriterWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(objectDetectionWorkerRequest)
                .then(networkRequestWorkerRequest)
                .then(databaseWriterWorkerRequest)
                .enqueue()
        }

        btnGroupChainSucceed.setOnClickListener {
            val objectDetectionWorkerRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val objectDetectionWorkerRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val networkRequestWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val databaseWriterWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()

            workManager.beginWith(listOf(objectDetectionWorkerRequest1, objectDetectionWorkerRequest2))
                .then(networkRequestWorkerRequest)
                .then(databaseWriterWorkerRequest)
                .enqueue()
        }

        btnGroupChainFail.setOnClickListener {
            val objectDetectionWorkerRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val objectDetectionWorkerRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to false))
                .build()
            val networkRequestWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()
            val databaseWriterWorkerRequest = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true))
                .build()


            workManager.beginWith(listOf(objectDetectionWorkerRequest1, objectDetectionWorkerRequest2))
                .then(networkRequestWorkerRequest)
                .then(databaseWriterWorkerRequest)
                .enqueue()
        }

        btnMultipleChainSucceed.setOnClickListener {
            val objectDetectionWorkerRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val objectDetectionWorkerRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()
            val networkRequestWorkerRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val networkRequestWorkerRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()
            val databaseWriterWorkerRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val databaseWriterWorkerRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()

            val recommendation1 = workManager.beginWith(objectDetectionWorkerRequest1)
                .then(networkRequestWorkerRequest1)
                .then(databaseWriterWorkerRequest1)

            val recommendation2 = workManager.beginWith(objectDetectionWorkerRequest2)
                .then(networkRequestWorkerRequest2)
                .then(databaseWriterWorkerRequest2)

            val root = WorkContinuation.combine(listOf(recommendation1, recommendation2))
            root.enqueue()
        }

        btnMultipleChainFail.setOnClickListener {
            val objectDetectionWorkerRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val objectDetectionWorkerRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()
            val networkRequestWorkerRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val networkRequestWorkerRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to false, "NAME" to "TWO"))
                .build()
            val databaseWriterWorkerRequest1 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "ONE"))
                .build()
            val databaseWriterWorkerRequest2 = OneTimeWorkRequestBuilder<ObjectDetectionWorker>()
                .setInputData(workDataOf("SUCCESS" to true, "NAME" to "TWO"))
                .build()

            val recommendation1 = workManager.beginWith(objectDetectionWorkerRequest1)
                .then(networkRequestWorkerRequest1)
                .then(databaseWriterWorkerRequest1)

            val recommendation2 = workManager.beginWith(objectDetectionWorkerRequest2)
                .then(networkRequestWorkerRequest2)
                .then(databaseWriterWorkerRequest2)

            val root = WorkContinuation.combine(listOf(recommendation1, recommendation2))
            root.enqueue()
        }
    }
}