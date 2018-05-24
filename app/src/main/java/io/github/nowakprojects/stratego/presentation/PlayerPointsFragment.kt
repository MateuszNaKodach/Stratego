package io.github.nowakprojects.stratego.presentation


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import io.github.nowakprojects.stratego.R
import io.github.nowakprojects.stratego.domain.stratego.Player
import io.github.nowakprojects.stratego.domain.stratego.PlayerMove
import io.github.nowakprojects.stratego.presentation.viewmodel.StrategoViewModel
import kotlinx.android.synthetic.main.fragment_player_points.*

class PlayerPointsFragment : Fragment() {

    private lateinit var viewModel: StrategoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_player_points, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StrategoViewModel::class.java)

        textView_firstPlayerPoints.text = "Player 1: 0"
        textView_secondPlayerPoints.text = "Player 2: 0"

        viewModel.moveLiveData.observe(this, Observer<PlayerMove> { playerMove: PlayerMove? ->
            if (playerMove != null) {
                val playerPoints = viewModel.stratego.getCurrentState().playerPoints
                textView_firstPlayerPoints.text = "Player 1: ${playerPoints[Player.FIRST]}"
                textView_secondPlayerPoints.text = "Player 2: ${playerPoints[Player.SECOND]}"
            }
        })
    }


    companion object {
        @JvmStatic
        fun newInstance() = PlayerPointsFragment()
    }
}
