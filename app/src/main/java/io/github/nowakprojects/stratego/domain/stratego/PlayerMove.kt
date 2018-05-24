package io.github.nowakprojects.stratego.domain.stratego

data class PlayerMove(val player: Player, val boardPoint: BoardPoint, val gainedPoints: Int = 0)