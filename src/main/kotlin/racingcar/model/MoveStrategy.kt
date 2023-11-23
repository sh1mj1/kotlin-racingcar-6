package racingcar.model

interface MoveStrategy {
    fun isMove(car: Car): Boolean
}