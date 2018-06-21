package model

import domain.Generator
import model.strategy.AltruistStrategy
import model.strategy.QuirkyStrategy
import model.strategy.RogueStrategy
import model.strategy.SpitefulStrategy
import model.strategy.TrickyStrategy
import model.strategy.UnpredictableStrategy
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Collections

/**
 * @author Renat Kaitmazov
 */

@RunWith(JUnit4::class)
class MerchantTest {

  private lateinit var altruist: Merchant
  private lateinit var rogue: Merchant
  private lateinit var unpredictable: Merchant
  private lateinit var tricky: Merchant
  private lateinit var quirky: Merchant
  private lateinit var spiteful: Merchant

  @Before
  fun setUp() {
    val id = 1
    altruist = Altruist(id, AltruistStrategy())
    rogue = Rogue(id, RogueStrategy())
    unpredictable = Unpredictable(id, UnpredictableStrategy(Generator))
    tricky = Tricky(id, TrickyStrategy())
    quirky = Quirky(id, QuirkyStrategy())
    spiteful = Spiteful(id, SpitefulStrategy())
  }

  @Test
  fun sortRichestMerchantsInDescendingOrderTest() {
    // 3
    val altruistCoins = 12
    altruist.lastYearEarnedCoins = altruistCoins

    // 6
    val rogueCoins = 4
    rogue.lastYearEarnedCoins = rogueCoins

    // 5
    val unpredictableCoins = 8
    unpredictable.lastYearEarnedCoins = unpredictableCoins

    // 1
    val trickyCoins = 16
    tricky.lastYearEarnedCoins = trickyCoins

    // 2
    val quirkyCoins = 15
    quirky.lastYearEarnedCoins = quirkyCoins

    // 4
    val spitefulCoins = 10
    spiteful.lastYearEarnedCoins = spitefulCoins

    val merchants = listOf(altruist, rogue, unpredictable, tricky, quirky, spiteful)
    Collections.sort(merchants, Merchant.BY_EARNED_COINS)

    assertEquals(trickyCoins, merchants[0].lastYearEarnedCoins)
    assertEquals(quirkyCoins, merchants[1].lastYearEarnedCoins)
    assertEquals(altruistCoins, merchants[2].lastYearEarnedCoins)
    assertEquals(spitefulCoins, merchants[3].lastYearEarnedCoins)
    assertEquals(unpredictableCoins, merchants[4].lastYearEarnedCoins)
    assertEquals(rogueCoins, merchants[5].lastYearEarnedCoins)
  }

  data class Person(val name: String)
}