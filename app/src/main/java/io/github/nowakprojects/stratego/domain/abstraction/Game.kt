package io.github.nowakprojects.stratego.domain.abstraction

interface Game {

    fun isTerminalState(): Boolean
    fun makeMove(player: Player)
}