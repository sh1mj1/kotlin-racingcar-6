package racingcar.model

import racingcar.config.ExceptionMessage.INVALID_CAR_NAME
import racingcar.config.ExceptionMessage.TOO_LONG_NAME
import racingcar.config.GameConfig.MAX_CAR_NAME_LENGTH
import racingcar.view.OutputView.Companion.CAR_POSITION_SYMBOL
import racingcar.view.OutputView.Companion.START_LANE

class Car(
    val name: String,
    var position: Int = 0,
    private val moveStrategy: MoveStrategy
) {
    fun move() {
        if (moveStrategy.isMove(this)) {
            position++
        }
    }

    fun validate() {
        when {
            name.isBlank() -> throw IllegalArgumentException(INVALID_CAR_NAME)
            name.length > MAX_CAR_NAME_LENGTH -> throw IllegalArgumentException(TOO_LONG_NAME)
        }
    }

    override fun toString(): String = "$name$START_LANE${CAR_POSITION_SYMBOL.repeat(position)}"
}