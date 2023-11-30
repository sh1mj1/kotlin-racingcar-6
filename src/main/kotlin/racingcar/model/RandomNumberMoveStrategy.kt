package racingcar.model

import camp.nextstep.edu.missionutils.Randoms.pickNumberInRange

class RandomNumberMoveStrategy : MoveStrategy {
    override fun canMoveForward(car: Car) =
        pickNumberInRange(RANDOM_START_RANGE, RANDOM_END_RANGE) >= RANDOM_NUMBER_CONDITION

    companion object {
        const val RANDOM_START_RANGE = 0
        const val RANDOM_END_RANGE = 9
        const val RANDOM_NUMBER_CONDITION = 4
    }
}