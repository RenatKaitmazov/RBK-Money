package model.strategy

import domain.Generator
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
class TrickyStrategyTest {

  private lateinit var trickyStrategy: TrickyStrategy

  @Before
  fun setUp() {
    trickyStrategy = TrickyStrategy()
  }

  @Test
  fun alwaysStartsWithCooperationTest() {
    trickyStrategy.onColleagueMove(true)
    assertFalse(trickyStrategy.isCheating())
  }

  @Test
  fun repeatsColleagueStrategyTest() {
    // No matter what, it never cheats for the first time.
    trickyStrategy.isCheating()
    val numberOfRuns = 10_000
    for (i in 0 until numberOfRuns) {
      val colleagueMove = Generator.boolean()
      trickyStrategy.onColleagueMove(colleagueMove)
      assertTrue(colleagueMove == trickyStrategy.isCheating())
    }
  }
}