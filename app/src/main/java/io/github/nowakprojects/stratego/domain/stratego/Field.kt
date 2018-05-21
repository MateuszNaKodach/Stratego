package io.github.nowakprojects.stratego.domain.stratego

sealed class Field;

class PlayerField(val player: Player):Field()

class FreeField:Field()