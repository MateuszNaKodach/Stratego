package io.github.nowakprojects.stratego.domain.stratego

sealed class Field(val rowIndex:RowIndex, val columnIndex:ColumnIndex){
    open fun toPlayerString() = "_"
    override fun toString() = "${toPlayerString()}(column(x)=$columnIndex,row(y)=$rowIndex,)"
}

class PlayerField(val player: Player, rowIndex: RowIndex, columnIndex: ColumnIndex):Field(rowIndex, columnIndex){
    override fun toPlayerString() = player.toShortString()
}

class FreeField(rowIndex: RowIndex, columnIndex: ColumnIndex):Field(rowIndex, columnIndex)