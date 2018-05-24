package io.github.nowakprojects.stratego.domain.stratego

class GameState(val board: Board, val currentPlayer: Player, val playerPoints: Map<Player, Int>, val lastMove: PlayerMove? = null) {

    fun generateAllAvailableNextStates(): List<GameState> {
        val states = mutableListOf<GameState>()
        for (rowIndexY in 0 until board.size) {
            for (columnIndexX in 0 until board.size) {
                if (board[rowIndexY][columnIndexX] is FreeField) {
                    val pointsForField = PointsCalculator(board, BoardPoint(rowIndexY, columnIndexX)).calculate()
                    val stateBoard = board.deepClone().apply { this[rowIndexY][columnIndexX] = PlayerField(currentPlayer, rowIndexY, columnIndexX) }
                    val updatedPoints = playerPoints.toMutableMap().apply { this[currentPlayer] = playerPoints[currentPlayer]?.plus(pointsForField)?:0 }
                    states.add(GameState(stateBoard, currentPlayer.opposite(), updatedPoints, PlayerMove(currentPlayer,BoardPoint(rowIndexY, columnIndexX))))
                }
            }
        }
        return states.sortedByDescending { it.playerPoints[currentPlayer] }
    }

}