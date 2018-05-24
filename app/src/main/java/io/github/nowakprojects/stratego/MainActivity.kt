package io.github.nowakprojects.stratego

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.nowakprojects.stratego.presentation.StrategoActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this,StrategoActivity::class.java))
    }
}
