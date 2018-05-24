package io.github.nowakprojects.stratego.domain.stratego

typealias Board = Array<Array<Field>>
typealias RowIndex = Int
typealias ColumnIndex = Int

object BoardBuilder {
    fun empty(n: Int) = Board(n, { rowIndex -> Array(n, { columnIndex -> FreeField(rowIndex, columnIndex) }) })
}

fun Board.row(rowIndex: Int): Array<Field> = this[rowIndex]

fun Board.column(columnIndex: Int): Array<Field> = this.map { it[columnIndex] }.toTypedArray()

fun Board.markField(player: Player, boardPoint: BoardPoint) {
    this[boardPoint.rowIndex][boardPoint.columnIndex] = PlayerField(player, boardPoint.rowIndex, boardPoint.columnIndex)
}

fun Board.toPlayersBoardString() =
        (0 until this.size).map { rowIndex -> row(rowIndex).toPlayerLineString() }
                .joinToString("\n") { it.toString().replace(",", "") }

fun Board.printBoardString() = println(this.toPlayersBoardString())

fun Array<Field>.toPlayerLineString() = this.map { it.toPlayerString() }

fun Array<Field>.toFieldLineString() = this.map { it.toString() }

fun Array<Field>.printFieldLineString() = println(this.toFieldLineString())

fun Array<Field>.printPlayerLineString() = println(this.toPlayerLineString())

// Diagonal \ from bottom-right to top-left
fun Board.leftDiagonalOf(rowIndex: RowIndex, columnIndex: ColumnIndex) =
        this.leftDiagonalOf(BoardPoint(rowIndex, columnIndex))

fun Board.leftDiagonalOf(boardPoint: BoardPoint): Array<Field> {
    val (rowIndex, columnIndex) = boardPoint
    val boardField = this[rowIndex][columnIndex]
    val boardSize = this.size

    fun toBottom(): Array<Field> {
        val result = mutableListOf<Field>()
        var currentRowIndex: RowIndex = rowIndex + 1
        var currentColumnIndex: ColumnIndex = columnIndex + 1
        while (currentRowIndex < boardSize && currentColumnIndex < boardSize) {
            result.add(this[currentRowIndex][currentColumnIndex])
            currentRowIndex++
            currentColumnIndex++
        }
        return result.toTypedArray()
    }

    fun toTop(): Array<Field> {
        val result = mutableListOf<Field>()
        var currentRowIndex: RowIndex = rowIndex - 1
        var currentColumnIndex: ColumnIndex = columnIndex - 1
        while (currentRowIndex > -1 && currentColumnIndex > -1) {
            result.add(this[currentRowIndex][currentColumnIndex])
            currentRowIndex--
            currentColumnIndex--
        }
        return result.toTypedArray()
    }

    return toTop().plus(toBottom()).plus(boardField)
            .sortedWith(compareBy({it.columnIndex},{it.rowIndex}))
            .toTypedArray()
}

// Diagonal / from bottom-left to top-right
fun Board.rightDiagonalOf(rowIndex: RowIndex, columnIndex: ColumnIndex) =
        this.rightDiagonalOf(BoardPoint(rowIndex, columnIndex))

fun Board.rightDiagonalOf(boardPoint: BoardPoint): Array<Field> {
    val (rowIndex, columnIndex) = boardPoint
    val boardField = this[rowIndex][columnIndex]
    val boardSize = this.size

    fun toTop(): Array<Field> {
        val result = mutableListOf<Field>()
        var currentRowIndex: RowIndex = rowIndex - 1
        var currentColumnIndex: ColumnIndex = columnIndex + 1
        while (currentRowIndex > -1 && currentColumnIndex < boardSize) {
            result.add(this[currentRowIndex][currentColumnIndex])
            currentRowIndex--
            currentColumnIndex++
        }
        return result.toTypedArray()
    }

    fun toBottom(): Array<Field> {
        val result = mutableListOf<Field>()
        var currentRowIndex: RowIndex = rowIndex + 1
        var currentColumnIndex: ColumnIndex = columnIndex - 1
        while (currentRowIndex < boardSize && currentColumnIndex > -1) {
            result.add(this[currentRowIndex][currentColumnIndex])
            currentRowIndex++
            currentColumnIndex--
        }
        return result.toTypedArray()
    }

    return toTop().plus(toBottom()).plus(boardField)
            .sortedWith(compareBy({it.columnIndex},{it.rowIndex}))
            .toTypedArray()
}

fun countFreeFieldsIn(line: Array<Field>) = line.count { it is FreeField }

fun Array<Field>.countFreeFields() = countFreeFieldsIn(this)

fun Board.countFreeFieldsInRow(rowIndex: Int) = this.row(rowIndex).count { it is FreeField }

fun Board.countFreeFieldsInColumn(columnIndex: Int) = this.column(columnIndex).count { it is FreeField }

fun isOnlyOneFreeIn(line: Array<Field>) = countFreeFieldsIn(line) == 1

fun Board.isOnlyOneFreeInRow(rowIndex: Int) = countFreeFieldsInRow(rowIndex) == 1

fun Board.isOnlyOneFreeInColumn(columnIndex: Int) = countFreeFieldsInColumn(columnIndex) == 1

