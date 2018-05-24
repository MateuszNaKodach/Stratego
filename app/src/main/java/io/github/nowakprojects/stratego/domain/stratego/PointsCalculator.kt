package io.github.nowakprojects.stratego.domain.stratego

//TODO: Optimalize calculations with multithreating (corountines or RxJava)!
class PointsCalculator(private val board: Board, private val boardPoint: BoardPoint) {

    private val row = board.row(boardPoint.rowIndex)
    private val column = board.column(boardPoint.columnIndex)

    private val horizontalPoints: Int by lazy {
        return@lazy if (isOnlyOneFreeIn(row)) row.size else 0
    }

    private val verticalPoints: Int by lazy {
        return@lazy if (isOnlyOneFreeIn(column)) column.size else 0
    }




}