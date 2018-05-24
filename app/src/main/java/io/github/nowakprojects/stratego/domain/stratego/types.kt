package io.github.nowakprojects.stratego.domain.stratego


typealias NodeValueHeuristic = (gameState: GameState, maxPlayer: Player, minPlayer: Player) -> Int

val pointsAdvantageHeuristic: NodeValueHeuristic = { gameState, maxPlayer, minPlayer ->
    gameState.playerPoints[maxPlayer]!! - gameState.playerPoints[minPlayer]!!
}

val maxPlayerPointsHeuristic: NodeValueHeuristic = { gameState, maxPlayer, _ -> gameState.playerPoints[maxPlayer]!! }
val minPlayerPointsHeuristic: NodeValueHeuristic = { gameState, _, minPlayer -> gameState.playerPoints[minPlayer]!! }



typealias GameTreeDepth = Int
