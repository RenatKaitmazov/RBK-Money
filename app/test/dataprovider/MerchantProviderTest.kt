package dataprovider

import domain.Generator
import model.Altruist
import model.Merchant
import model.Quirky
import model.Rogue
import model.Spiteful
import model.Tricky
import model.Unpredictable
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.Collections

/**
 * @author Renat Kaitmazov
 */

@RunWith(JUnit4::class)
class MerchantProviderTest {

  @Test
  fun createsEvenAmountOfMerchantsOfAllTypes() {
    val merchants = MerchantProvider.newMerchantsList()
    assertEquals(60, merchants.size)

    var altruistCount = 0
    var rogueCount = 0
    var unpredictableCount = 0
    var trickyCount = 0
    var quirkyCount = 0
    var spitefulCount = 0

    for (merchant in merchants) {
      when (merchant) {
        is Altruist -> ++altruistCount
        is Rogue -> ++rogueCount
        is Unpredictable -> ++unpredictableCount
        is Tricky -> ++trickyCount
        is Quirky -> ++quirkyCount
        is Spiteful -> ++spitefulCount
      }
    }

    val count = 10
    assertEquals(count, altruistCount)
    assertEquals(count, rogueCount)
    assertEquals(count, unpredictableCount)
    assertEquals(count, trickyCount)
    assertEquals(count, quirkyCount)
    assertEquals(count, spitefulCount)
  }

  @Test
  fun poorestMerchantsReplacementTest() {
    val merchants = MerchantProvider.newMerchantsList()
    val size = merchants.size
    // 20% of poorest merchants should be replaced
    val amountToReplace = (size * 0.2).toInt()
    // Make the first 12 merchant richer
    for (i in 0 until amountToReplace) {
      val coins = Generator.numberInRange(100, 500)
      merchants[i].lastYearEarnedCoins = coins
    }
    // Sort them in descending order
    Collections.sort(merchants, Merchant.BY_EARNED_COINS)
    // Replace the the bottom 20%.
    // The type of the new ones must be the same as the type of the top 20% merchants.
    MerchantProvider.replacePoorestMerchants(merchants)
    val offset = size - amountToReplace
    for (i in 0 until amountToReplace) {
      assertEquals(merchants[i]::class, merchants[i + offset]::class)
    }
  }
}