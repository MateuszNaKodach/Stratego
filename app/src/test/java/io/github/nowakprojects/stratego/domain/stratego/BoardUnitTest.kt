package io.github.nowakprojects.stratego.domain.stratego

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


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
        board.leftDiagonalOf(BoardPoint(2,1)).printFieldLineString()
        board.rightDiagonalOf(BoardPoint(2,1)).printFieldLineString()
    }
}