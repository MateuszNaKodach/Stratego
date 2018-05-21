package io.github.nowakprojects.stratego.domain.stratego

typealias Board = Array<Array<Field>>

object BoardBuilder {
    fun empty(n: Int) = Board(n, { Array(n, { FreeField() }) })
}

typealias StateValueHeuristic = (gameState: GameState, maxPlayer: Player, minPlayer: Player) -> Int
typealias GameTreeDepth = Int
