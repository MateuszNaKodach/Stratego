package io.github.nowakprojects.stratego.presentation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.nowakprojects.stratego.domain.stratego.BoardPoint
import io.github.nowakprojects.stratego.domain.stratego.PlayerMove
import io.github.nowakprojects.stratego.domain.stratego.Stratego
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class StrategoViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val depth = 3
    val stratego = Stratego(6, { println("GAME FINISHED!") })
    var boardEnabled = true

    val moveLiveData: MutableLiveData<PlayerMove> = MutableLiveData()
    //TODO: Add points live data!

    fun makeMove(boardPoint: BoardPoint) {
        if (boardEnabled) {
            val move = stratego.makeMove(boardPoint)
            if (move != null) {
                moveLiveData.value = move
            }

            Single.fromCallable({ stratego.makeAutoMove(depth) })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe { boardEnabled = false }
                    .doOnSuccess { boardEnabled = true }
                    .subscribeBy(onSuccess = { moveLiveData.value = it })
        }
    }
}
