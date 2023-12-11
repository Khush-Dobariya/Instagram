package com.example.instagram

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.Models.Reel
import com.example.instagram.Utils.POST_FOLDER
import com.example.instagram.Utils.REEL
import com.example.instagram.Utils.REEL_FOLDER
import com.example.instagram.Utils.uploadImage
import com.example.instagram.Utils.uploadVideo
import com.example.instagram.databinding.ActivityReelsBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class ReelsActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityReelsBinding.inflate(layoutInflater)
    }
    private lateinit var VidioUrl: String
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadVideo(uri, REEL_FOLDER) { url ->
                if (url != null) {

                    VidioUrl = url
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.selectReel.setOnClickListener {
            launcher.launch("video/*")
        }
        binding.cancelButton.setOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }
        binding.postButoon.setOnClickListener {
            val reel: Reel = Reel(VidioUrl!!, binding.caption.editText?.text.toString())

            Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).document()
                    .set(reel)
                    .addOnSuccessListener {
                        startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
                        finish()
                    }
            }
        }
    }
}