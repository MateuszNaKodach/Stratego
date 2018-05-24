package io.github.nowakprojects.stratego.domain.stratego

class MinimaxAlgorithm(val gameState: GameState, val maxDepth: Int, val nodeValueHeuristic: NodeValueHeuristic) {

    private val maxPlayer = gameState.currentPlayer
    private val minPlayer = gameState.currentPlayer.opposite()

    val bestState = execute(gameState, 0)?.gameState

    private fun execute(gameState: GameState, currentDepth: Int): StateNode? {
        val availableNextStates: List<GameState> =
                if (currentDepth != maxDepth)
                    gameState.generateAllAvailableNextStates()
                else
                    emptyList()

        return when {
            availableNextStates.isEmpty() -> returnValue(currentDepth, gameState)
            else -> nodeFrom(availableNextStates, currentDepth, gameState, if (gameState.currentPlayer == maxPlayer) ::max else ::min)
        }

    }

    protected fun returnValue(currentDepth: Int, gameState: GameState): StateNode? {
        return if (currentDepth == 0) {
            null
        } else {
            val value = nodeValueHeuristic(gameState, maxPlayer, minPlayer)
            return StateNode(gameState, value)
        }
    }

    private fun nodeFrom(gameStates: List<GameState>,
                         currentDepth: Int,
                         gameState: GameState,
                         bestChildSelector: (List<StateNode>) -> StateNode
    ): StateNode {
        val children = gameStates.map { execute(it, currentDepth + 1)!! }
        val bestChild = bestChildSelector(children)
        return if (currentDepth == 0) bestChild else StateNode(gameState, bestChild.value)
    }

    private fun max(stateNodes: List<StateNode>): StateNode = stateNodes.maxBy { it.value }!!

    private fun min(stateNodes: List<StateNode>): StateNode = stateNodes.minBy { it.value }!!

}