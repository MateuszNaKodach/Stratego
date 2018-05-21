package io.github.nowakprojects.stratego.domain.interfaces

interface Game {

    fun isTerminalState(): Boolean
    fun makeMove(player: Player)
}