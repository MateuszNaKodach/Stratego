package io.github.nowakprojects.stratego.presentation

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.nowakprojects.stratego.R
import io.github.nowakprojects.stratego.presentation.viewmodel.StrategoViewModel

class StrategoActivity : AppCompatActivity() {

    private lateinit var viewModel: StrategoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stratego_activity)
        viewModel = ViewModelProviders.of(this).get(StrategoViewModel::class.java)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_gameBoard, GameBoardFragment.newInstance())
                    .commitNow()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container_playerPoints, PlayerPointsFragment.newInstance())
                    .commitNow()
        }
    }

}
