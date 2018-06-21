package model.strategy

import domain.Generator
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Renat Kaitmazov
 */

@RunWith(JUnit4::class)
class UnpredictableStrategyTest {

  private lateinit var unpredictableStrategy: UnpredictableStrategy

  @Before
  fun setUp() {
    unpredictableStrategy = UnpredictableStrategy(Generator)
  }

  @Test
  fun unpredictableStrategyIsUnpredictableTest() {
    val numberOfRuns = 10_000
    var numberOfCheatedDeals = 0
    for (i in 0 until numberOfRuns) {
      if (unpredictableStrategy.isCheating()) {
        ++numberOfCheatedDeals
      }
    }
    assertTrue(numberOfCheatedDeals != numberOfRuns)
    assertTrue(numberOfCheatedDeals > 0)
  }
}