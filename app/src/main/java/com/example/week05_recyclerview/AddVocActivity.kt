package com.example.week05_recyclerview

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.week05_recyclerview.databinding.ActivityAddVocBinding
import com.example.week05_recyclerview.databinding.ActivityIntroBinding
import java.io.PrintStream

class AddVocActivity : AppCompatActivity() {
    lateinit var binding:ActivityAddVocBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVocBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    fun init() {
        binding.addbtn.setOnClickListener {
            val word = binding.word.text.toString()
            val meaning = binding.meaning.text.toString()
            // 저장
            val output = PrintStream(openFileOutput("voc.txt", Context.MODE_APPEND))
            output.println(word)
            output.println(meaning)
            output.close()
            val intent = Intent()
            intent.putExtra("voc", MyData(word, meaning, View.GONE))
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        binding.cancelbtn.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}