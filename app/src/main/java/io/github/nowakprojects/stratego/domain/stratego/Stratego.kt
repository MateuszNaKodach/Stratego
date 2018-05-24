package io.github.nowakprojects.stratego.domain.stratego

import java.util.*

class Stratego(val boardSize: Int = 8) {

    val board: Board = BoardBuilder.empty(boardSize)

    var playerPoints = hashMapOf(Player.FIRST to 0, Player.SECOND to 0)

    val movesHistory = Stack<PlayerMove>()

    var filledField = 0
}