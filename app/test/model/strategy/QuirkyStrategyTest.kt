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
class QuirkyStrategyTest {

  private lateinit var quirkyStrategy: QuirkyStrategy

  @Before
  fun setUp() {
    quirkyStrategy = QuirkyStrategy()
  }

  @Test
  fun alwaysStartsWithEstablishedSequenceOfMoves() {
    assertFalse(quirkyStrategy.isCheating())
    assertTrue(quirkyStrategy.isCheating())
    assertFalse(quirkyStrategy.isCheating())
    assertFalse(quirkyStrategy.isCheating())
  }

  @Test
  fun becomesRogueIfHasBeenDeceived() {
    quirkyStrategy.onColleagueMove(false)
    assertFalse(quirkyStrategy.isCheating())
    quirkyStrategy.onColleagueMove(false)
    assertTrue(quirkyStrategy.isCheating())
    // The merchant was deceived.
    quirkyStrategy.onColleagueMove(true)
    assertFalse(quirkyStrategy.isCheating())
    quirkyStrategy.onColleagueMove(false)
    assertFalse(quirkyStrategy.isCheating())

    for (i in 0 until 10_000) {
      val colleagueMove = Generator.boolean()
      quirkyStrategy.onColleagueMove(colleagueMove)
      assertTrue(quirkyStrategy.isCheating())
    }
  }

  @Test
  fun becomesTrickyIfHasNotBeenDeceived() {
    quirkyStrategy.onColleagueMove(false)
    assertFalse(quirkyStrategy.isCheating())
    quirkyStrategy.onColleagueMove(false)
    assertTrue(quirkyStrategy.isCheating())
    quirkyStrategy.onColleagueMove(false)
    assertFalse(quirkyStrategy.isCheating())
    quirkyStrategy.onColleagueMove(false)
    assertFalse(quirkyStrategy.isCheating())

    // A tricky merchant always starts with a cooperation
    assertFalse(quirkyStrategy.isCheating())

    for (i in 0 until 10_000) {
      val colleagueMove = Generator.boolean()
      quirkyStrategy.onColleagueMove(colleagueMove)
      assertTrue(quirkyStrategy.isCheating() == colleagueMove)
    }
  }
}