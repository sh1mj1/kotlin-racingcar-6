package racingcar.model

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
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

    // TODO: 가장 큰 position 을 가진 car 의 이름(들)을 리스트로 리턴하는 테스트
//    @ParameterizedTest
//    @MethodSource("provideTestData")
//    fun `가장 큰 position 을 가진 car 의 이름(들)을 리스트로 리턴한다`(data: TestData) {
//        val result = data.cars.decideWinner()
//        Assertions.assertThat(result).isEqualTo(data.expected)
//    }

    companion object {
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
    }
}
