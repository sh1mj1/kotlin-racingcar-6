package racingcar.model

import camp.nextstep.edu.missionutils.Randoms
import racingcar.config.GameConfig.END_INCLUSIVE
import racingcar.config.GameConfig.START_INCLUSIVE

class NumberGeneratorImp : NumberGenerator {
    override fun generateRandomNumber(): Int = Randoms.pickNumberInRange(START_INCLUSIVE, END_INCLUSIVE)
}