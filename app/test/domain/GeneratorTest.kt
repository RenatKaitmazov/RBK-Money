package domain

import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Renat Kaitmazov
 */

@RunWith(JUnit4::class)
class GeneratorTest {

  @Test(expected = IllegalArgumentException::class)
  fun throwsExceptionIfLowerBoundIsGreaterThanUpperBound() {
    Generator.numberInRange(100, 10)
  }

  @Test(expected = IllegalArgumentException::class)
  fun throwsExceptionIfLowerBoundEqualsUpperBoundAndUpperBoundNotIncluded() {
    Generator.numberInRange(10, 10 ,false)
  }

  @Test
  fun generatesInGivenClosedRangeTest() {
    var lowerBound = 50
    var upperBound = 50
    for (i in 0 until 1_000) {
      val randomNumber = Generator.numberInRange(lowerBound, upperBound)
      assertTrue(randomNumber == 50)
    }

    lowerBound = 100
    upperBound = 130
    for (i in 0 until 1_000) {
      val randomNumber = Generator.numberInRange(lowerBound, upperBound)
      assertTrue(randomNumber >= lowerBound)
      assertTrue(randomNumber <= upperBound)
    }

    lowerBound = 0
    upperBound = 1
    var numberOfOnes = 0
    for (i in 0 until 1_000) {
      val randomNumber = Generator.numberInRange(lowerBound, upperBound)
      if (randomNumber == 1) {
        ++numberOfOnes
      }
      assertTrue(randomNumber >= lowerBound)
      assertTrue(randomNumber <= upperBound)
    }
    assertTrue(numberOfOnes > 0)
  }

  @Test
  fun generatesInGivenHalfOpenRangeTest() {
    var lowerBound = 1_123
    var upperBound = 1_592
    for (i in 0 until 1_000) {
      val randomNumber = Generator.numberInRange(lowerBound, upperBound, inclusive = false)
      assertTrue(randomNumber >= lowerBound)
      assertTrue(randomNumber < upperBound)
    }

    lowerBound = 0
    upperBound = 1
    for (i in 0 until 1_000) {
      val randomNumber = Generator.numberInRange(lowerBound, upperBound, inclusive = false)
      assertTrue(randomNumber == 0)
    }
  }
}