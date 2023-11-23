package racingcar.model

import camp.nextstep.edu.missionutils.Randoms
import racingcar.config.GameConfig
import racingcar.config.GameConfig.MIN_THRESHOLD

class UsingRandomNumberMoveStrategy : MoveStrategy {
    override fun isMove(car: Car): Boolean {
        val randomNumber = Randoms.pickNumberInRange(
            GameConfig.START_INCLUSIVE,
            GameConfig.END_INCLUSIVE
        )
        if (randomNumber >= MIN_THRESHOLD) {
            return true
        }
        return false
    }
}