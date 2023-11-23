package racingcar.model

import racingcar.config.ExceptionMessage.DUPLICATED_NAME

class Cars(inputCars: List<String>, moveStrategy: MoveStrategy = UsingRandomNumberMoveStrategy()) {

    private var carList: List<Car>

    init {
        carList = inputCars.map { Car(name = it, moveStrategy = moveStrategy) }
    }

    fun move() {
        carList.forEach {
            it.move()
        }
    }

    fun decideWinner(): List<String> {
        val maxPosition = carList.maxOf(Car::position)
        return carList.filter {
            it.position == maxPosition
        }.map(Car::name).toList()
    }

    fun validate() {
        validateDuplicate()
        carList.forEach {
            it.validate()
        }
    }

    internal fun validateDuplicate() {
        val set = carList.map(Car::name).toSet()
        if (carList.size > set.size) {
            throw IllegalArgumentException(DUPLICATED_NAME)
        }
    }

    override fun toString(): String =
        carList.joinToString("\n") { it.toString() }
}