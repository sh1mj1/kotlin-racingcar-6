package racingcar.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import racingcar.config.ExceptionMessage.DUPLICATED_NAME
import java.util.stream.Stream

class CarsTest {

    @ParameterizedTest
    @MethodSource("provideDuplicatedCars")
    fun `중복된 이름이 있으면 예외를 던진다`(cars: Cars) {
        assertThrows<IllegalArgumentException>(DUPLICATED_NAME) {
            cars.validateDuplicate()
        }
    }

    @ParameterizedTest
    @MethodSource("provideUniqueCars")
    fun `중복된 이름이 없으면 테스트를 통과한다`(cars: Cars) {
        assertDoesNotThrow { cars.validateDuplicate() }
    }

    @ParameterizedTest
    @MethodSource("provideCarsAndMockStrategy")
    fun `가장 큰 position 을 가진 car 의 이름(들)을 리스트로 리턴한다`(cars: Cars, expected: List<String>) {
        // when
        cars.move()
        val result = cars.decideWinner()

        // then
        assertThat(result).isEqualTo(expected)
    }

    companion object {
        private val mockMoveStrategy: MoveStrategy = object : MoveStrategy {
            override fun isMove(car: Car): Boolean = car.name.contains("pobi")
        }

        @JvmStatic
        fun provideUniqueCars(): Stream<Cars> = Stream.of(
            Cars(listOf("pobi", "woni", "jun")),
            Cars(listOf("pobi", "woni", "jun", "sh1m")),
            Cars(listOf("pobi")),
        )

        @JvmStatic
        fun provideDuplicatedCars(): Stream<Cars> = Stream.of(
            Cars(listOf("pobi", "pobi")),
            Cars(listOf("pobi", "woni", "jun", "pobi")),
            Cars(listOf("pobi", "woni", "jun", "jun")),
        )

        @JvmStatic
        fun provideCarsAndMockStrategy(): Stream<Arguments> = Stream.of(
            Arguments.of(Cars(listOf("pobi", "woni", "jun"), mockMoveStrategy), listOf("pobi")),
            Arguments.of(Cars(listOf("pobi1", "pobi2", "jun"), mockMoveStrategy), listOf("pobi1", "pobi2")),
            Arguments.of(Cars(listOf("pobi1", "pobi2", "pobi3"), mockMoveStrategy), listOf("pobi1", "pobi2", "pobi3")),
        )
    }
}
