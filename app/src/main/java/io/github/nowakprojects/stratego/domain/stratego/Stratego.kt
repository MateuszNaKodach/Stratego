package io.github.nowakprojects.stratego.domain.stratego

import java.util.*

class Stratego(val boardSize: Int = 8, val gameFinishedCallback: () -> Unit) {

    private var board: Board = BoardBuilder.empty(boardSize)

    private var playerPoints = mutableMapOf(Player.FIRST to 0, Player.SECOND to 0)

    private val movesHistory = Stack<PlayerMove>()

    private var filledFields = 0

    private var currentPlayer: Player = Player.FIRST

    fun getCurrentState() = GameState(board.deepClone(), Player.valueOf(currentPlayer.name), playerPoints.toMap())

    fun makeMove(boardPoint: BoardPoint) {
        val (rowIndexY, columnIndexX) = boardPoint
        var field = board[rowIndexY][columnIndexX]
        if (field is FreeField) {
            field = PlayerField(currentPlayer, rowIndexY, columnIndexX)
            val gainedPoints = PointsCalculator(board, boardPoint).calculate()
            playerPoints[currentPlayer] = (playerPoints[currentPlayer] ?: 0).plus(gainedPoints)
            commitPlayerMove(PlayerMove(currentPlayer, boardPoint, gainedPoints))
        }
    }

    private fun commitPlayerMove(playerMove: PlayerMove) {
        movesHistory.push(playerMove)
        filledFields++
        if (isGameFinished()) {
            gameFinishedCallback()
        } else {
            currentPlayer = currentPlayer.opposite()
        }
    }

    fun makeAutoMove(maxDepth: Int) {
        val newBestState = MinimaxAlgorithm(getCurrentState(), maxDepth, maxPlayerPointsHeuristic).bestState!!
        board = newBestState.board
        playerPoints = newBestState.playerPoints.toMutableMap()
        commitPlayerMove(newBestState.lastMove!!)
    }

    fun isGameFinished() = filledFields == boardSize * boardSize
}