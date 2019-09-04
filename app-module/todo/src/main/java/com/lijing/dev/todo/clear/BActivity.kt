package com.lijing.dev.todo.clear

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lijing.dev.todo.R
import kotlinx.android.synthetic.main.activity_b.*

class BActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        tv_jump.setOnClickListener {
            val intent = Intent(this, CActivity::class.java)
            startActivity(intent)
        }
    }
}
