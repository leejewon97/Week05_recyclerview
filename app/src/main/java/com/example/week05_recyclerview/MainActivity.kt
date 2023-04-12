package com.example.week05_recyclerview

import MyDataAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.*
import com.example.week05_recyclerview.databinding.ActivityMainBinding
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var datas:ArrayList<MyData> = ArrayList()
    lateinit var adapter: MyDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuitem1 -> {
                binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            }
            R.id.menuitem2 -> {
                binding.recyclerView.layoutManager = GridLayoutManager(this, 3)
            }
            R.id.menuitem3 -> {
                binding.recyclerView.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun initRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MyDataAdapter(datas)
        adapter.itemClickListener = object:MyDataAdapter.OnItemClickListener{
            override fun onItemClick(data: MyData, adapterPosition: Int) {
                data.visibility = when (data.visibility) {
                    View.GONE -> View.VISIBLE
                    else -> View.GONE
                }
                adapter.notifyItemChanged(adapterPosition)
            }
        }
        binding.recyclerView.adapter = adapter
    }
    fun initData(){
        try {
            val scan2 = Scanner(openFileInput("voc.txt"))
            while (scan2.hasNextLine()){
                val word = scan2.nextLine()
                val mean = scan2.nextLine()
                datas.add(MyData(word, mean, View.GONE))
            }
        } catch (e:IOException) {
            Toast.makeText(this, "CATCH", Toast.LENGTH_SHORT).show()
        }

        // IOException e
        val scan = Scanner(resources.openRawResource(R.raw.words))
        while (scan.hasNextLine()){
            val word = scan.nextLine()
            val mean = scan.nextLine()
            datas.add(MyData(word, mean, View.GONE))
        }
    }
}
