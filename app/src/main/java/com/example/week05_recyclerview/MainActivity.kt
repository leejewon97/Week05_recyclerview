package com.example.week05_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week05_recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var data:ArrayList<MyData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initRecyclerView()
    }
    fun initRecyclerView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = MyDataAdapter(data)
    }
    fun initData(){
        data.add(MyData("item1", 10))
        data.add(MyData("item2", 5))
        data.add(MyData("item3", 13))
        data.add(MyData("item4", 8))
        data.add(MyData("item5", 18))
        data.add(MyData("item6", 9))
        data.add(MyData("item7", 15))
        data.add(MyData("item8", 7))
        data.add(MyData("item9", 20))
    }
}