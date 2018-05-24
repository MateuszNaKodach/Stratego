package io.github.nowakprojects.stratego.domain.stratego

sealed class Field(open val rowIndexY:RowIndexY, open val columnIndexX:ColumnIndexX){
    open fun toPlayerString() = "_"
    override fun toString() = "${toPlayerString()}(column(x)=$columnIndexX,row(y)=$rowIndexY,)"
}

data class PlayerField(val player: Player, override val rowIndexY: RowIndexY, override val columnIndexX: ColumnIndexX):Field(rowIndexY, columnIndexX){
    override fun toPlayerString() = player.toShortString()
}

data class FreeField(override val rowIndexY: RowIndexY, override val columnIndexX: ColumnIndexX):Field(rowIndexY, columnIndexX)