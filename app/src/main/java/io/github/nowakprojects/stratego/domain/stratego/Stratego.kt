package io.github.nowakprojects.stratego.domain.stratego

import java.util.*

class Stratego(val boardSize: Int = 8, val gameFinishedCallback: () -> Unit = {}) {

    private var board: Board = BoardBuilder.empty(boardSize)

    private var playerPoints = mutableMapOf(Player.FIRST to 0, Player.SECOND to 0)

    val movesHistory = Stack<PlayerMove>()

    private var filledFields = 0

    private var currentPlayer: Player = Player.FIRST

    fun getCurrentState() = GameState(board.deepClone(), Player.valueOf(currentPlayer.name), playerPoints.toMap())

    fun makeMove(boardPoint: BoardPoint):PlayerMove? {
        val (rowIndexY, columnIndexX) = boardPoint
        if (board[rowIndexY][columnIndexX] is FreeField) {
            val gainedPoints = PointsCalculator(board, boardPoint).calculate()
            board[rowIndexY][columnIndexX] = PlayerField(currentPlayer, rowIndexY, columnIndexX)
            playerPoints[currentPlayer] = (playerPoints[currentPlayer] ?: 0).plus(gainedPoints)
            val playerMove = PlayerMove(currentPlayer, boardPoint, gainedPoints)
            commitPlayerMove(playerMove)
            return playerMove
        }
        return null;
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

    fun makeAutoMove(maxDepth: Int):PlayerMove {
        val newBestState = MinimaxAlgorithm(getCurrentState(), maxDepth, pointsAdvantageHeuristic).bestState!!
        board = newBestState.board
        playerPoints = newBestState.playerPoints.toMutableMap()
        val move = newBestState.lastMove!!
        commitPlayerMove(move)
        return move
    }

    fun isGameFinished() = filledFields == boardSize * boardSize
}