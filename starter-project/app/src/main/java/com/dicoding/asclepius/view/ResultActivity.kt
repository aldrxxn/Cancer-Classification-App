package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.helper.ImageClassifierHelper
import org.tensorflow.lite.task.vision.classifier.Classifications

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
//class ResultActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityResultBinding
    companion object {
        const val IMAGE_URI = "img_uri"
        const val TAG = "imagePicker"
        const val RESULT_TEXT = "result_text"
        const val REQUEST_HISTORY_UPDATE = 1
    }







//    companion object {
//        const val IMAGE_URI = "img_uri"
//        const val TAG = "imagePicker"
//        const val RESULT_TEXT = "result_text"
//        const val REQUEST_HISTORY_UPDATE = 1
//    }    companion object {
//        const val IMAGE_URI = "img_uri"
//        const val TAG = "imagePicker"
//        const val RESULT_TEXT = "result_text"
//        const val REQUEST_HISTORY_UPDATE = 1
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUriString = intent.getStringExtra(IMAGE_URI)
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            displayImage(imageUri)
            classifyImage(imageUri)
        } else {
            Log.e(TAG, "No image URI provided")
            finish()
        }
    }

    private fun displayImage(uri: Uri) { //    private fun displayImage(uri: Uri) {

        Log.d(TAG, "Displaying image: $uri")
        binding.resultImage.setImageURI(uri)
    }









//    private fun displayImage(uri: Uri) {
//        private fun displayImage(uri: Uri) {

        private fun classifyImage(imageUri: Uri) {
        val imageClassifierHelper = ImageClassifierHelper(
            contextValue = this,
            classifierListenerValue = object : ImageClassifierHelper.ClassifierListener {
                override fun onError(errorMsg: String) {
                    Log.d(TAG, "Error: $errorMsg")
                    showToast(errorMsg)
                }

                override fun onResults(results: List<Classifications>?, inferenceTime: Long) {
                    results?.let { showResults(it) }
                }
            }
        )
        imageClassifierHelper.classifyStaticImage(imageUri)
    }

    private fun showResults(results: List<Classifications>) {
        val topResult = results[0]
        val label = topResult.categories[0].label
        val score = topResult.categories[0].score
        //   private fun showResults(results: List<Classifications>) {
        //        val topResult = results[0]
        //        val label = topResult.categories[0].label
        //        val score = topResult.categories[0].score

        fun Float.formatToString(): String {
            return String.format("%.2f%%", this * 100)
        }
        binding.resultText.text = "$label ${score.formatToString()}"
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    //    private fun showToast(message: String) {
    //        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    //    }
    //}//    private fun showToast(message: String) {
    //        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    //    }
    //}//    private fun showToast(message: String) {
    //        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    //    }
    //}
}
