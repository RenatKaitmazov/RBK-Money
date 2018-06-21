package domain

import java.util.Random

/**
 * A random number generator.
 *
 * @author Renat Kaitmazov
 */

object Generator {

  /*----------* Properties *----------*/

  private val generator = Random(System.nanoTime())

  /*----------* API *----------*/

  /**
   * Randomly generates a boolean value.
   * @return true or false generated randomly.
   */
  fun boolean() = generator.nextBoolean()

  /**
   * Generates a random number in the range of [lowerBound] and [upperBound].
   * The [upperBound] is inclusive by default.
   *
   * @param lowerBound the lower bound of the range. The generated number can't be less than it.
   * @param upperBound the upper bound of the range. The generated number can't be greater than it.
   * @param inclusive specifies if the upper bound should be included or not. By default, it is included.
   * @return a random number in the range [[lowerBound], [upperBound]] or [[lowerBound], [upperBound])
   * if the upper bound is not included.
   */
  fun numberInRange(lowerBound: Int, upperBound: Int, inclusive: Boolean = true): Int {
    if (lowerBound > upperBound) {
      throw IllegalArgumentException("Lower bound is greater than upper bound")
    }
    if (lowerBound == upperBound && !inclusive) {
      throw IllegalArgumentException("No integer in the range [$lowerBound, $upperBound)")
    }
    val delta = upperBound - lowerBound
    val finalUpperBound = if (inclusive) delta + 1 else delta
    return generator.nextInt(finalUpperBound) + lowerBound
  }
}