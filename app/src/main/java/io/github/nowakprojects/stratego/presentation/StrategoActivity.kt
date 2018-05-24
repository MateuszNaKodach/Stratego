package io.github.nowakprojects.stratego.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.nowakprojects.stratego.R

class StrategoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stratego_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, GameBoardFragment.newInstance())
                    .commitNow()
        }
    }

}
