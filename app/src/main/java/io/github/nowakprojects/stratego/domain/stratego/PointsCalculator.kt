package io.github.nowakprojects.stratego.domain.stratego

//TODO: Optimalize calculations with multithreating (corountines or RxJava)!
class PointsCalculator(private val board: Board, private val boardPoint: BoardPoint) {

    private val horizontalPoints: Int by lazy {
        val row = board.row(boardPoint.rowIndexY)
        return@lazy countPointsForFieldsLine(row)
    }

    private val verticalPoints: Int by lazy {
        val column = board.column(boardPoint.columnIndexX)
        return@lazy countPointsForFieldsLine(column)
    }

    private val leftDiagonalPoints: Int by lazy {
        val leftDiagonal = board.leftDiagonalOf(boardPoint)
        return@lazy countPointsForFieldsLine(leftDiagonal)
    }

    private val rightDiagonalPoints: Int by lazy {
        val rightDiagonal = board.rightDiagonalOf(boardPoint)
        return@lazy countPointsForFieldsLine(rightDiagonal)
    }

    private fun countPointsForFieldsLine(fields: Array<Field>) =
            if (isOnlyOneFreeIn(fields)) fields.size else 0

    fun calculate() = horizontalPoints + verticalPoints + leftDiagonalPoints + rightDiagonalPoints

}