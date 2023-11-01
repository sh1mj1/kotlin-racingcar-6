package racingcar.controller

import racingcar.model.Car
import racingcar.model.Cars
import racingcar.view.InputView
import racingcar.view.OutputCars
import racingcar.view.OutputGuide
import racingcar.view.OutputResult

class GameController {
    private val outputGuide = OutputGuide()
    private val inputView = InputView()
    private val outputCars = OutputCars()
    private val outputResult = OutputResult()

    private lateinit var cars: Cars

    fun startGame() {
        outputGuide.showStartGuide()
        cars = Cars(inputView.inputCars().map { Car(it) })
        cars.validate()

        outputGuide.showTryCountGuide()
        val tryCount = inputView.inputTryCount()
        startTurn(tryCount)
        val winners = cars.decideWinner()
        outputResult.showResult(winners)

    }

    private fun startTurn(tryCount: Int) {
        outputGuide.showResultGuide()
        for (i in 1..tryCount) {
            cars.cars.forEach {
                it.moveForward()
            }
            outputCars.showRace(cars)
        }
    }

}