package io.github.nowakprojects.stratego.domain.stratego


typealias StateValueHeuristic = (gameState: GameState, maxPlayer: Player, minPlayer: Player) -> Int
typealias GameTreeDepth = Int
