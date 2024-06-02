package com.dicoding.asclepius.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yalantis.ucrop.UCrop
import java.io.File
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    private var currentImageUri: Uri? = null
//    private var croppedImageUri: Uri? = null
//
//    companion object {
//        const val TAG = "ImagePicker"
//        private const val REQUEST_RESULT = 1001
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.galleryButton.setOnClickListener { startGallery() }
//        binding.analyzeButton.setOnClickListener {
//            currentImageUri?.let {
//                analyzeImage()
//                moveToResult()
//            } ?: run {
//                showToast(getString(R.string.image_classifier_failed))
//            }
//        }
//    }
//
//    private fun startGallery() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        val chooser = Intent.createChooser(intent, "pilih gambar yang ada")
//        launcherIntentGallery.launch(chooser)
//    }
//
//    private val launcherIntentGallery = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == RESULT_OK) {
//            val selectedImg = result.data?.data
//            selectedImg?.let { uri ->
//                currentImageUri = uri
//                showImage()
//                startUCrop(uri)
//            } ?: showToast("Gagal import gambar")
//        }
//    }
//
//    private fun startUCrop(sourceUri: Uri) {
//        val fileName = "cropped_image_${System.currentTimeMillis()}.jpg"
//        val destinationUri = Uri.fromFile(File(cacheDir, fileName))
//        UCrop.of(sourceUri, destinationUri)
//            .withAspectRatio(1f, 1f)
//            .withMaxResultSize(1000, 1000)
//            .start(this)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
//            val resulUri = UCrop.getOutput(data!!)
//            resulUri?.let {
//                showCroppedImage(resulUri)
//            } ?: showToast("Gagal memotong gambar")
//        } else if (resultCode == UCrop.RESULT_ERROR) {
//            val cropError = UCrop.getError(data!!)
//            showToast("Crop error: ${cropError?.message}")
//        }
//    }
//
//    private fun showCroppedImage(uri: Uri) {
//        binding.previewImageView.setImageURI(uri)
//        croppedImageUri = uri
//    }
//
//    private fun showImage() {
//        currentImageUri?.let { uri ->
//            Log.d(TAG, "Displaying image: $uri")
//            binding.previewImageView.setImageURI(uri)
//        } ?: Log.d(TAG, "Gambar tidak tersedia")
//    }
//
//    private fun analyzeImage() {
//        val intent = Intent(this, ResultActivity::class.java)
//        croppedImageUri?.let { uri ->
//            intent.putExtra(ResultActivity.IMAGE_URI, uri.toString())
//            startActivityForResult(intent, REQUEST_RESULT)
//        } ?: showToast(getString(R.string.image_classifier_failed))
//    }
//
//    private fun moveToResult() {
//        Log.d(TAG, "Moving to ResultActivity")
//        analyzeImage()
//    }
//
//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//}
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var currentImageUri: Uri? = null
    private var croppedImageUri: Uri? = null

    companion object {
        const val TAG = "ImagePicker"
        private const val REQUEST_RESULT = 1001
    }
    //    override fun onCreate(savedInstanceState: Bundle?) {
    //        super.onCreate(savedInstanceState)
    //        binding = ActivityMainBinding.inflate(layoutInflater)
    //        setContentView(binding.root)
    //        binding.galleryButton.setOnClickListener { startGallery() }
    //        binding.analyzeButton.setOnClickListener {
    //            currentImageUri?.let {
    //                analyzeImage()
    //                moveToResult()
    //            } ?: run {
    //                showToast(getString(R.string.image_classifier_failed))
    //            }
    //        }
    //    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.galleryButton.setOnClickListener { startGallery() }
        binding.analyzeButton.setOnClickListener {
            currentImageUri?.let {
                analyzeImage()
                moveToResult()
            } ?: run {
                showToast(getString(R.string.image_classifier_failed))
            }
        }
    }
    //    override fun onCreate(savedInstanceState: Bundle?) {
    //        super.onCreate(savedInstanceState)
    //        binding = ActivityMainBinding.inflate(layoutInflater)
    //        setContentView(binding.root)
    //        binding.galleryButton.setOnClickListener { startGallery() }
    //        binding.analyzeButton.setOnClickListener {
    //            currentImageUri?.let {
    //                analyzeImage()
    //                moveToResult()
    //            } ?: run {
    //                showToast(getString(R.string.image_classifier_failed))
    //            }
    //        }
    //    }

    private fun startGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "pilih gambar yang ada")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg = result.data?.data
            selectedImg?.let { uri ->
                currentImageUri = uri
                showImage()
                startUCrop(uri)
            } ?: showToast("Gagal import gambar")
        }
    }

    private fun startUCrop(sourceUri: Uri) {
        val fileName = "cropped_image_${System.currentTimeMillis()}.jpg"
        val destinationUri = Uri.fromFile(File(cacheDir, fileName))
        UCrop.of(sourceUri, destinationUri)
            .withAspectRatio(1f, 1f)
            .withMaxResultSize(1000, 1000)
            .start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
            val resulUri = UCrop.getOutput(data!!)
            resulUri?.let {
                showCroppedImage(resulUri)
            } ?: showToast("Gagal memotong gambar")
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
            showToast("Crop error: ${cropError?.message}")
        }
    }

    private fun showCroppedImage(uri: Uri) {
        binding.previewImageView.setImageURI(uri)
        croppedImageUri = uri
    }

    private fun showImage() {
        currentImageUri?.let { uri ->
            Log.d(TAG, "Displaying image: $uri")
            binding.previewImageView.setImageURI(uri)
        } ?: Log.d(TAG, "Gambar tidak tersedia")
    }
    //    private fun showImage() {
    //        currentImageUri?.let { uri ->
    //            Log.d(TAG, "Displaying image: $uri")
    //            binding.previewImageView.setImageURI(uri)
    //        } ?: Log.d(TAG, "Gambar tidak tersedia")
    //    }

    private fun analyzeImage() {
        val intent = Intent(this, ResultActivity::class.java)
        croppedImageUri?.let { uri ->
            intent.putExtra(ResultActivity.IMAGE_URI, uri.toString())
            startActivityForResult(intent, REQUEST_RESULT)
        } ?: showToast(getString(R.string.image_classifier_failed))
    }//
    //    private fun showImage() {
    //        currentImageUri?.let { uri ->
    //            Log.d(TAG, "Displaying image: $uri")
    //            binding.previewImageView.setImageURI(uri)
    //        } ?: Log.d(TAG, "Gambar tidak tersedia")
    //    }

    private fun moveToResult() {
        Log.d(TAG, "Moving to ResultActivity")
        analyzeImage()
    }
    //    private fun moveToResult() {
    //        Log.d(TAG, "Moving to ResultActivity")
    //        analyzeImage()
    //    }
    //    private fun moveToResult() {
    //        Log.d(TAG, "Moving to ResultActivity")
    //        analyzeImage()
    //    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    private var currentImageUri: Uri? = null
//    private var croppedImageUri: Uri? = null
//
//    companion object {
//        const val TAG = "ImagePicker"
//        private const val REQUEST_RESULT = 1001
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.galleryButton.setOnClickListener { startGallery() }
//        binding.analyzeButton.setOnClickListener {
//            currentImageUri?.let {
//                analyzeImage()
//                moveToResult()
//            } ?: run {
//                showToast(getString(R.string.image_classifier_failed))
//            }
//        }
//    }
//
//    private fun startGallery() {
//        val intent = Intent(Intent.ACTION_GET_CONTENT)
//        intent.type = "image/*"
//        val chooser = Intent.createChooser(intent, "pilih gambar yang ada")
//        launcherIntentGallery.launch(chooser)
//    }
//
//    private val launcherIntentGallery = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == RESULT_OK) {
//            val selectedImg = result.data?.data
//            selectedImg?.let { uri ->
//                currentImageUri = uri
//                showImage()
//                startUCrop(uri)
//            } ?: showToast("Gagal import gambar")
//        }
//    }
//
//    private fun startUCrop(sourceUri: Uri) {
//        val fileName = "cropped_image_${System.currentTimeMillis()}.jpg"
//        val destinationUri = Uri.fromFile(File(cacheDir, fileName))
//        UCrop.of(sourceUri, destinationUri)
//            .withAspectRatio(1f, 1f)
//            .withMaxResultSize(1000, 1000)
//            .start(this)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == UCrop.REQUEST_CROP && resultCode == RESULT_OK) {
//            val resulUri = UCrop.getOutput(data!!)
//            resulUri?.let {
//                showCroppedImage(resulUri)
//            } ?: showToast("Gagal memotong gambar")
//        } else if (resultCode == UCrop.RESULT_ERROR) {
//            val cropError = UCrop.getError(data!!)
//            showToast("Crop error: ${cropError?.message}")
//        }
//    }
//
//    private fun showCroppedImage(uri: Uri) {
//        binding.previewImageView.setImageURI(uri)
//        croppedImageUri = uri
//    }
//
//    private fun showImage() {
//        currentImageUri?.let { uri ->
//            Log.d(TAG, "Displaying image: $uri")
//            binding.previewImageView.setImageURI(uri)
//        } ?: Log.d(TAG, "Gambar tidak tersedia")
//    }
//
//    private fun analyzeImage() {
//        val intent = Intent(this, ResultActivity::class.java)
//        croppedImageUri?.let { uri ->
//            intent.putExtra(ResultActivity.IMAGE_URI, uri.toString())
//            startActivityForResult(intent, REQUEST_RESULT)
//        } ?: showToast(getString(R.string.image_classifier_failed))
//    }
//
//    private fun moveToResult() {
//        Log.d(TAG, "Moving to ResultActivity")
//        analyzeImage()
//    }
//
//    private fun showToast(message: String) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//    }
//}
