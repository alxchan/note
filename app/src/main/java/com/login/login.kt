package com.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.login.MainActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.testing.login.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginConfirm.setOnClickListener{
            auth.signInWithEmailAndPassword(binding.email.text.toString(), binding.password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this@login, MainActivity::class.java))
                    } else{
                        Log.d("Invalid Login","Bro idk how you mess this up")
                    }
                }
        }


    }
}
