package racingcar.model

import camp.nextstep.edu.missionutils.Randoms

class NumberGenerator {
    fun generateRandomNumber(): Int {
        return Randoms.pickNumberInRange(0, 9)
    }
}