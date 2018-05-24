package io.github.nowakprojects.stratego.presentation.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayout
import android.util.AttributeSet
import android.view.View
import io.github.nowakprojects.stratego.R
import io.github.nowakprojects.stratego.domain.stratego.Field
import io.github.nowakprojects.stratego.domain.stratego.FreeField
import io.github.nowakprojects.stratego.domain.stratego.Player
import io.github.nowakprojects.stratego.domain.stratego.PlayerField

class TileView : View {
    lateinit var field: Field
    private var tileWidth: Int = 0
    private var tileHeight: Int = 0
    private var margin: Int = 0


    constructor(context: Context, width: Int, height: Int, margin: Int, rowIndex: Int, columnIndex: Int) : super(context) {
        this.tileWidth = width
        this.tileHeight = height
        this.margin = margin
        this.field = FreeField(rowIndex, columnIndex)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorTileGrey))
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val params = this.layoutParams
        params as GridLayout.LayoutParams
        params.width = tileWidth
        params.height = tileHeight
        params.marginEnd = margin
        params.bottomMargin = margin
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @Suppress("unused")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun markByPlayer(player: Player){
        field = PlayerField(player,field.rowIndexY,field.columnIndexX)
        when(player){
            Player.FIRST -> setBackgroundColor(ContextCompat.getColor(context,R.color.colorTileBlue))
            Player.SECOND -> setBackgroundColor(ContextCompat.getColor(context,R.color.colorTileOrange))
        }
        isEnabled = false
    }
}
