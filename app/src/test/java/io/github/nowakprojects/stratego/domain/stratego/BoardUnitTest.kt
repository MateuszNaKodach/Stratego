package io.github.nowakprojects.stratego.domain.stratego

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.*


class BoardUnitTest {

    @Test
    fun `initialized board should have all fields free`() {
        val boardSize = 6
        val board = BoardBuilder.empty(boardSize)
        (0 until boardSize).forEach {
            assertThat(board.countFreeFieldsInRow(it)).isEqualTo(boardSize)
            assertThat(board.countFreeFieldsInColumn(it)).isEqualTo(boardSize)
        }
    }

    @Test
    fun `marking board should place player marks in selected places`() {
        val boardSize = 6
        val board = BoardBuilder.empty(boardSize)
        board.markField(Player.FIRST, BoardPoint(1, 1))
        board.markField(Player.SECOND, BoardPoint(2, 1))
        board.markField(Player.FIRST, BoardPoint(3, 4))
        board.markField(Player.SECOND, BoardPoint(0, 0))
        assertThat(board.toPlayersBoardString()).isEqualTo(
                "[S _ _ _ _ _]\n" +
                        "[_ F _ _ _ _]\n" +
                        "[_ S _ _ _ _]\n" +
                        "[_ _ _ _ F _]\n" +
                        "[_ _ _ _ _ _]\n" +
                        "[_ _ _ _ _ _]"
        )
        //board.leftDiagonalOf(BoardPoint(2,1)).printFieldLineString()
        //board.rightDiagonalOf(BoardPoint(2,1)).printFieldLineString()
    }

    @Test
    fun `generating game states`() {
        val boardSize = 6
        val board = BoardBuilder.empty(boardSize)
        board.markField(Player.FIRST, BoardPoint(1, 1))
        board.markField(Player.SECOND, BoardPoint(2, 1))
        board.markField(Player.FIRST, BoardPoint(3, 4))
        board.markField(Player.SECOND, BoardPoint(0, 0))
        val states = GameState(board, Player.FIRST, mapOf(Player.FIRST to 0, Player.SECOND to 0))
                .generateAllAvailableNextStates()
        assertThat(states.size).isEqualTo(32)
    }

    @Test
    fun `generating game states 2`() {
        val boardSize = 6
        val board = BoardBuilder.empty(boardSize)
        board.markField(Player.FIRST, BoardPoint(1, 1))
        board.markField(Player.SECOND, BoardPoint(2, 1))
        board.markField(Player.FIRST, BoardPoint(3, 4))
        board.markField(Player.SECOND, BoardPoint(0, 0))
        board.markField(Player.FIRST, BoardPoint(3, 1))
        board.markField(Player.SECOND, BoardPoint(4, 1))
        board.markField(Player.FIRST, BoardPoint(5, 1))
        board.markField(Player.FIRST, BoardPoint(0, 2))
        board.markField(Player.FIRST, BoardPoint(0, 3))
        board.markField(Player.FIRST, BoardPoint(0, 4))
        board.markField(Player.FIRST, BoardPoint(0, 5))

        val currentState = GameState(board, Player.FIRST, mapOf(Player.FIRST to 0, Player.SECOND to 0))
        val best = MinimaxAlgorithm(currentState, 3, minPlayerPointsHeuristic).bestState

    }

    @Test
    fun playing() {
        val stratego = Stratego(6, { println("FINISHED!") })
        while (!stratego.isGameFinished()) {
            stratego.getCurrentState().board.printBoardString()
            stratego.makeAutoMove(3)
            stratego.getCurrentState().board.printBoardString()
            val row = readLine()!!.toInt()
            val column = readLine()!!.toInt()
            stratego.makeMove(BoardPoint(row, column))
            stratego.getCurrentState().board.printBoardString()
        }
    }
}