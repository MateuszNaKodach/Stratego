package io.github.nowakprojects.stratego.domain.stratego

typealias Board = Array<Array<Field>>
typealias RowIndexY = Int
typealias ColumnIndexX = Int

object BoardBuilder {
    fun empty(n: Int) = Board(n, { rowIndex -> Array(n, { columnIndex -> FreeField(rowIndex, columnIndex) }) })
}

fun Board.deepClone() = this.copyOf().map { it.clone() }.toTypedArray()

fun Board.row(rowIndex: Int): Array<Field> = this[rowIndex]

fun Board.column(columnIndex: Int): Array<Field> = this.map { it[columnIndex] }.toTypedArray()

fun Board.getPoint(boardPoint: BoardPoint) = this[boardPoint.rowIndexY][boardPoint.columnIndexX]

fun Board.markField(player: Player, boardPoint: BoardPoint) {
    this[boardPoint.rowIndexY][boardPoint.columnIndexX] = PlayerField(player, boardPoint.rowIndexY, boardPoint.columnIndexX)
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
fun Board.leftDiagonalOf(rowIndexY: RowIndexY, columnIndexX: ColumnIndexX) =
        this.leftDiagonalOf(BoardPoint(rowIndexY, columnIndexX))

fun Board.leftDiagonalOf(boardPoint: BoardPoint): Array<Field> {
    val (rowIndex, columnIndex) = boardPoint
    val boardField = this[rowIndex][columnIndex]
    val boardSize = this.size

    fun toBottom(): Array<Field> {
        val result = mutableListOf<Field>()
        var currentRowIndexY: RowIndexY = rowIndex + 1
        var currentColumnIndexX: ColumnIndexX = columnIndex + 1
        while (currentRowIndexY < boardSize && currentColumnIndexX < boardSize) {
            result.add(this[currentRowIndexY][currentColumnIndexX])
            currentRowIndexY++
            currentColumnIndexX++
        }
        return result.toTypedArray()
    }

    fun toTop(): Array<Field> {
        val result = mutableListOf<Field>()
        var currentRowIndexY: RowIndexY = rowIndex - 1
        var currentColumnIndexX: ColumnIndexX = columnIndex - 1
        while (currentRowIndexY > -1 && currentColumnIndexX > -1) {
            result.add(this[currentRowIndexY][currentColumnIndexX])
            currentRowIndexY--
            currentColumnIndexX--
        }
        return result.toTypedArray()
    }

    return toTop().plus(toBottom()).plus(boardField)
            .sortedWith(compareBy({ it.columnIndexX }, { it.rowIndexY }))
            .toTypedArray()
}

// Diagonal / from bottom-left to top-right
fun Board.rightDiagonalOf(rowIndexY: RowIndexY, columnIndexX: ColumnIndexX) =
        this.rightDiagonalOf(BoardPoint(rowIndexY, columnIndexX))

fun Board.rightDiagonalOf(boardPoint: BoardPoint): Array<Field> {
    val (rowIndex, columnIndex) = boardPoint
    val boardField = this[rowIndex][columnIndex]
    val boardSize = this.size

    fun toTop(): Array<Field> {
        val result = mutableListOf<Field>()
        var currentRowIndexY: RowIndexY = rowIndex - 1
        var currentColumnIndexX: ColumnIndexX = columnIndex + 1
        while (currentRowIndexY > -1 && currentColumnIndexX < boardSize) {
            result.add(this[currentRowIndexY][currentColumnIndexX])
            currentRowIndexY--
            currentColumnIndexX++
        }
        return result.toTypedArray()
    }

    fun toBottom(): Array<Field> {
        val result = mutableListOf<Field>()
        var currentRowIndexY: RowIndexY = rowIndex + 1
        var currentColumnIndexX: ColumnIndexX = columnIndex - 1
        while (currentRowIndexY < boardSize && currentColumnIndexX > -1) {
            result.add(this[currentRowIndexY][currentColumnIndexX])
            currentRowIndexY++
            currentColumnIndexX--
        }
        return result.toTypedArray()
    }

    return toTop().plus(toBottom()).plus(boardField)
            .sortedWith(compareBy({ it.columnIndexX }, { it.rowIndexY }))
            .toTypedArray()
}

fun countFreeFieldsIn(line: Array<Field>) = line.count { it is FreeField }

fun Array<Field>.countFreeFields() = countFreeFieldsIn(this)

fun Board.countFreeFieldsInRow(rowIndex: Int) = countFreeFieldsIn(this.row(rowIndex))

fun Board.countFreeFieldsInColumn(columnIndex: Int) = countFreeFieldsIn(this.column(columnIndex))

//TODO: Change method to better name - there was a bug - if line had only one elements, it
fun isOnlyOneFreeIn(line: Array<Field>) = line.size>1 && countFreeFieldsIn(line) == 1

fun Board.isOnlyOneFreeInRow(rowIndex: Int) = countFreeFieldsInRow(rowIndex) == 1

fun Board.isOnlyOneFreeInColumn(columnIndex: Int) = countFreeFieldsInColumn(columnIndex) == 1

