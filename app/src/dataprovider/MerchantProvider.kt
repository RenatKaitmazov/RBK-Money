package dataprovider

import domain.Generator
import model.Altruist
import model.Merchant
import model.Optimal
import model.Quirky
import model.Rogue
import model.Spiteful
import model.Tricky
import model.Unpredictable
import model.strategy.AltruistStrategy
import model.strategy.OptimalStrategy
import model.strategy.QuirkyStrategy
import model.strategy.RogueStrategy
import model.strategy.SpitefulStrategy
import model.strategy.TrickyStrategy
import model.strategy.UnpredictableStrategy

/**
 * A data provider that provides instances of [Merchant] class.
 *
 * @author Renat Kaitmazov
 */

internal object MerchantProvider {

  /*----------* Constants *----------*/

  private const val ALTRUIST_TYPE_ID = 0
  private const val ROGUE_TYPE_ID = 1
  private const val UNPREDICTABLE_TYPE_ID = 2
  private const val TRICKY_TYPE_ID = 3
  private const val SPITEFUL_TYPE_ID = 4
  private const val QUIRKY_TYPE_ID = 5
  private const val OPTIMAL_TYPE_ID = 6

  private const val TWENTY_PERCENT = 0.2

  /**
   * The amount of different [Merchant] types.
   */
  private const val NUMBER_OF_TYPES = 7

  /**
   * The amount of merchants to create per each type.
   */
  private const val PER_TYPE_COUNT = 10

  /*----------* Properties *----------*/

  private var altruistCount = 0
  private var rogueCount = 0
  private var unpredictableCount = 0
  private var trickyCount = 0
  private var spitefulCount = 0
  private var quirkyCount = 0
  private var optimalCount = 0

  /*----------* API *----------*/

  /**
   * Creates a list of merchant of six different types.
   * 60 merchants in total, 10 per type.
   * @return a list of merchants
   */
  fun newMerchantsList(): MutableList<Merchant> {
    val merchants = ArrayList<Merchant>(NUMBER_OF_TYPES * PER_TYPE_COUNT)
    for (typeId in 0 until NUMBER_OF_TYPES) {
      for (i in 0 until PER_TYPE_COUNT) {
        val merchant = newMerchant(typeId)
        merchants.add(merchant)
      }
    }
    return merchants
  }

  /**
   * At the end of every year 20% of the poorest merchants are excluded from the Guild.
   * Their place is taken by other merchants whose type is the same as the type of 20% of the
   * richest merchants.
   * This method replaces bottom 20% merchants with new ones.
   * The method assumes that [merchants] is sorted by the amount of coins earned in descending order.
   * @param merchants a list of merchants.
   */
  fun replacePoorestMerchants(merchants: MutableList<Merchant>) {
    val size = merchants.size
    // bottom 20% merchants
    val amountToReplace = (TWENTY_PERCENT * size).toInt()
    val offset = size - amountToReplace
    for (i in 0 until amountToReplace) {
      val richMerchant = merchants[i]
      val richMerchantTypeId = inferMerchantTypeId(richMerchant)
      val newMerchant = newMerchant(richMerchantTypeId)
      merchants[i + offset] = newMerchant
    }
  }

  /*----------* Helper methods *----------*/

  private fun newMerchant(typeId: Int) = when (typeId) {
    ALTRUIST_TYPE_ID -> Altruist(id = ++altruistCount, strategy = AltruistStrategy())
    ROGUE_TYPE_ID -> Rogue(id = ++rogueCount, strategy = RogueStrategy())
    UNPREDICTABLE_TYPE_ID -> Unpredictable(id = ++unpredictableCount, strategy = UnpredictableStrategy(Generator))
    TRICKY_TYPE_ID -> Tricky(id = ++trickyCount, strategy = TrickyStrategy())
    SPITEFUL_TYPE_ID -> Spiteful(id = ++spitefulCount, strategy = SpitefulStrategy())
    QUIRKY_TYPE_ID -> Quirky(id = ++quirkyCount, strategy = QuirkyStrategy())
    OPTIMAL_TYPE_ID -> Optimal(id = ++optimalCount, strategy = OptimalStrategy())
    else -> throw IllegalArgumentException("Unknown type id: $typeId")
  }

  private fun inferMerchantTypeId(merchant: Merchant) = when (merchant) {
    is Altruist -> ALTRUIST_TYPE_ID
    is Rogue -> ROGUE_TYPE_ID
    is Unpredictable -> UNPREDICTABLE_TYPE_ID
    is Tricky -> TRICKY_TYPE_ID
    is Spiteful -> SPITEFUL_TYPE_ID
    is Quirky -> QUIRKY_TYPE_ID
    is Optimal -> OPTIMAL_TYPE_ID
    else -> throw IllegalArgumentException("Unknown merchant type: ${merchant.javaClass.simpleName}")
  }
}