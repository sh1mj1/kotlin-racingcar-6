package racingcar.model

import camp.nextstep.edu.missionutils.Randoms

open class NumberGenerator {
    open fun generateRandomNumber(): Int = Randoms.pickNumberInRange(0, 9)
}