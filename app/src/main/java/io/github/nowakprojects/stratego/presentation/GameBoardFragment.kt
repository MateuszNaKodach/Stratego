package io.github.nowakprojects.stratego.presentation

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.nowakprojects.stratego.R
import io.github.nowakprojects.stratego.presentation.viewmodel.StrategoViewModel

class GameBoardFragment : Fragment() {

    companion object {
        fun newInstance() = GameBoardFragment()
    }

    private lateinit var viewModel: StrategoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.game_board_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StrategoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
