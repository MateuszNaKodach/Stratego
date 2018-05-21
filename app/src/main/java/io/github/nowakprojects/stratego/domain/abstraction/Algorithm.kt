package io.github.nowakprojects.stratego.domain.abstraction

import io.github.nowakprojects.stratego.domain.stratego.GameState
import io.github.nowakprojects.stratego.domain.stratego.GameTreeDepth
import io.github.nowakprojects.stratego.domain.stratego.StateValueHeuristic

abstract class Algorithm(
        gameState: GameState,
        val maxDepth: GameTreeDepth,
        val stateValueHeuristic: StateValueHeuristic
) {
}