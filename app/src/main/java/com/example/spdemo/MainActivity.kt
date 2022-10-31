package com.example.spdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var  nameText: EditText
    lateinit var ageText: EditText// 初始化时候可以不赋值 类似于 ios ?
    lateinit var sp: SharedPreferences//这是一个存储用的东西 全app内共通 存储和写入数据  类似于UnserDefault key-value形式保存
    // 数据被保存在文件目录data文件夹下
    // The android operating system, store shared
    //preferences of each applicaiton as seperate files
    //inside the "data folder of the phone.
    lateinit var editor: SharedPreferences.Editor//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.etName)//通过ID关联UI和代码
        ageText = findViewById(R.id.etAge)

        sp = getSharedPreferences("my_sf", MODE_PRIVATE)//存储的key是my_sf
        editor = sp.edit()
    }

    override fun onPause() {//app到后台前 存储数据到SharedPreferences
        super.onPause()
        val name = nameText.text.toString()//处理用户输入字符
        val age = ageText.text.toString().toInt()//处理用户输入字符
        editor.apply {
            putString("sf_name", name)
            putInt("sf_age", age)
            commit()//?????? editor的系统函数,保存修改
        }
    }

    override fun onResume() {//app恢复台前 从SharedPreferences读取数据
        super.onResume()
        val name = sp.getString("sf_name", null)
        val age = sp.getInt("sf_age", 0)
        nameText.setText(name)
        if (age != 0) {
            ageText.setText(age.toString())
        }
    }
}