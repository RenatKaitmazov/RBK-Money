package model.strategy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Renat Kaitmazov
 */

@RunWith(JUnit4::class)
class SpitefulStrategyTest {

  private lateinit var spitefulStrategy: SpitefulStrategy

  @Before
  fun setUp() {
    spitefulStrategy = SpitefulStrategy()
  }

  @Test
  fun alwaysCooperatesIfNotDeceivedTest() {
    val numberOfRuns = 10_000
    for (i in 0 until numberOfRuns) {
      assertFalse(spitefulStrategy.isCheating())
    }
  }

  @Test
  fun alwaysCheatsIfDeceivedTest() {
    assertFalse(spitefulStrategy.isCheating())
    // The spiteful merchant is deceived by his colleague, so he must change its strategy.
    // Now the merchant always cheats.
    spitefulStrategy.onColleagueMove(true)
    val numberOfRuns = 10_000
    for (i in 0 until numberOfRuns) {
      assertTrue(spitefulStrategy.isCheating())
    }
  }

  @Test
  fun alwaysStartsWithCooperation() {
    // The merchant has been deceived
    spitefulStrategy.onColleagueMove(true)
    // but despite that he must not cheat when making the very first deal.
    assertFalse(spitefulStrategy.isCheating())
    // But he can cheat after if he was deceived.
    assertTrue(spitefulStrategy.isCheating())
  }
}