package io.github.nowakprojects.stratego.domain.stratego

enum class Player {
    FIRST,
    SECOND;

    fun nextPlayer() = Player.values()[(this.ordinal + 1) % 2]
}