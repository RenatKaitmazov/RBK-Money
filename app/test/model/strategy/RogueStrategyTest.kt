package model.strategy

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Renat Kaitmazov
 */

@RunWith(JUnit4::class)
class RogueStrategyTest {

  private lateinit var rogueStrategy: RogueStrategy

  @Before
  fun setUp() {
    rogueStrategy = RogueStrategy()
  }

  @Test
  fun rogueAlwaysCheatsTest() {
    val numberOfRuns = 10_000
    for (i in 0 until numberOfRuns) {
      rogueStrategy.onColleagueMove(false)
      assertTrue(rogueStrategy.isCheating())
    }
  }
}