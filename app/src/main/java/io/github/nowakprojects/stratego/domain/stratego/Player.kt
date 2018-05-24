package io.github.nowakprojects.stratego.domain.stratego

enum class Player {
    FIRST,
    SECOND;

    fun opposite() = Player.values()[(this.ordinal + 1) % 2]

    fun toShortString(): String {
        return when(this){
            Player.FIRST -> "F"
            Player.SECOND -> "S"
        }
    }
}