package io.github.nowakprojects.stratego.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.ToggleButton
import io.github.nowakprojects.stratego.R
import io.github.nowakprojects.stratego.domain.stratego.*
import io.github.nowakprojects.stratego.presentation.view.TileView
import io.github.nowakprojects.stratego.presentation.viewmodel.StrategoViewModel
import kotlinx.android.synthetic.main.game_board_fragment.*

class GameBoardFragment : Fragment() {

    companion object {
        fun newInstance() = GameBoardFragment()
    }

    private lateinit var viewModel: StrategoViewModel
    private val gameBoard: GridLayout by lazy { gridLayout_GameBoard }
    private lateinit var boardCells: Array<Array<TileView>>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.game_board_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StrategoViewModel::class.java)
        val boardSize = viewModel.stratego.boardSize
        gameBoard.columnCount = boardSize

        val tileWidth = resources.getDimension(R.dimen.tiles_tile_width).toInt()
        val tileHeight = resources.getDimension(R.dimen.tiles_tile_height).toInt()
        val spaceBetweenTiles = resources.getDimension(R.dimen.tiles_space_between).toInt()

        boardCells = Array(boardSize, { rowIndexY ->
            Array(boardSize, { columnIndexX ->
                TileView(context!!, tileWidth, tileHeight, spaceBetweenTiles, rowIndexY, columnIndexX)
                        .apply {
                            setOnClickListener { viewModel.makeMove(BoardPoint(rowIndexY, columnIndexX)) }
                        }
            })
        })

        boardCells.forEach { it.forEach { cell -> gameBoard.addView(cell) } }

        viewModel.moveLiveData.observe(this, Observer<PlayerMove> { playerMove: PlayerMove? ->
            if (playerMove != null) {
                val (rowIndexY, columnIndexX) = playerMove.boardPoint
                boardCells[rowIndexY][columnIndexX].markByPlayer(playerMove.player)
                val playerPoints = viewModel.stratego.getCurrentState().playerPoints
                Toast.makeText(context, "FirstPlayer: ${playerPoints[Player.FIRST]} SecondPlayer: ${playerPoints[Player.SECOND]}", Toast.LENGTH_SHORT).show()
            }
        })


    }

}
