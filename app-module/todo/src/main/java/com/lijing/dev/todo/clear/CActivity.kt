package com.lijing.dev.todo.clear

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lijing.dev.todo.MainActivity
import com.lijing.dev.todo.R
import kotlinx.android.synthetic.main.activity_c.*

class CActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)
        tv_finish.setOnClickListener {
            // finish() 直接 finish 只能退回 B Activity
            Intent(this, MainActivity::class.java).let {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP.and(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(it)
            }
        }
    }
}