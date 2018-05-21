package io.github.nowakprojects.stratego.domain.stratego

typealias Board = List<List<Boolean>>
typealias StateValueHeuristic = (gameState:GameState, maxPlayer: Player, minPlayer: Player) -> Int