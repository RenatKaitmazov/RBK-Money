package model.strategy

import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Renat Kaitmazov
 */

@RunWith(JUnit4::class)
class AltruistStrategyTest {

  private lateinit var altruistStrategy: AltruistStrategy

  @Before
  fun setUp() {
    altruistStrategy = AltruistStrategy()
  }

  @Test
  fun altruistAlwaysCooperatesTest() {
    val numberOfRuns = 10_000
    for (i in 0 until numberOfRuns) {
      altruistStrategy.onColleagueMove(true)
      assertFalse(altruistStrategy.isCheating())
    }
  }
}