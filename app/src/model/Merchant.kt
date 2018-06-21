package model

import model.strategy.MerchantStrategy

/**
 * This base class contains the logic common to all types of merchants.
 *
 * @author Renat Kaitmazov
 */
abstract class Merchant(
    /**
     * The id property is used for debugging purposes.
     */
    private val id: Int,
    protected val strategy: MerchantStrategy
) {

  companion object {

    /*----------* Properties *----------*/

    /**
     * This comparator allows to sort merchants based on the coins they have earned.
     * The order is descending, that is the richest merchant is the first in the list.
     */
    @JvmStatic
    val BY_EARNED_COINS = Comparator<Merchant> { lhs, rhs -> rhs.lastYearEarnedCoins - lhs.lastYearEarnedCoins }
  }

  /*----------* Properties *----------*/

  /**
   * The amount of coins earned over one year
   */
  var lastYearEarnedCoins: Int = 0

  /*----------* Parent methods *----------*/

  override fun toString(): String = "${javaClass.simpleName}#$id{coins=$lastYearEarnedCoins}"

  /*----------* API *----------*/

  fun isCheating() = strategy.isCheating()

  fun onColleagueMove(hasCheated: Boolean, merchant: Merchant) = strategy.onColleagueMove(hasCheated)
}