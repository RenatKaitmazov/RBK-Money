package domain

import dataprovider.MerchantProvider
import model.Merchant
import java.util.Collections

/**
 * This class simulates deals between different types of merchants
 * and tries to determine the best strategy for making deals to gain the
 * maximum profit.
 *
 * @author Renat Kaitmazov
 */

class DealSimulator internal constructor(
    private val merchantProvider: MerchantProvider,
    private val dealMaker: DealMaker,
    /**
     * Every year each pair of merchants makes the given amount of deals.
     * Must be in the range of [5, 10]
     */
    private val amountOfDealsPerYear: Int
) {

  /*----------* Properties *----------*/

  private val merchants = merchantProvider.newMerchantsList()

  /*----------* Initialization *----------*/

  init {
    if (amountOfDealsPerYear < 5) throw IllegalArgumentException("Amount of deals must be at least 5")
    if (amountOfDealsPerYear > 10) throw IllegalArgumentException("Amount of deals must be at most 5")
    // Randomly distribute different types of merchants in the list.
    merchants.shuffle()
  }

  /*----------* API *----------*/

  /**
   * Makes deals between each pair of merchants [amountOfDealsPerYear] number of times
   * for [amountOfYears] years.
   * @param amountOfYears how many years to simulate.
   */
  fun simulate(amountOfYears: Int) {
    val merchantsCount = merchants.size
    for (year in 0 until amountOfYears) {
      for (i in 0 until merchantsCount) {
        for (j in i + 1 until merchantsCount) {
          // Make deals between each pair.
          val merchant1 = merchants[i]
          val merchant2 = merchants[j]
          makeDeal(merchant1, merchant2)
        }
      }
      Collections.sort(merchants, Merchant.BY_EARNED_COINS)
      merchantProvider.replacePoorestMerchants(merchants)
      resetEarnedCoins()
    }
    printMerchants()
  }

  /*----------* Helper methods *----------*/

  private fun makeDeal(merchant1: Merchant, merchant2: Merchant) {
    for (deal in 0 until amountOfDealsPerYear) {
      // Each merchant should get a chance to be an initiator of a deal.
      val (initiator, nonInitiator) = if (deal % 2 == 0) {
        merchant1 to merchant2
      } else {
        merchant2 to merchant1
      }
      dealMaker.makeDeal(initiator, nonInitiator)
    }
  }

  private fun resetEarnedCoins() {
    merchants.forEach { it.lastYearEarnedCoins = 0 }
  }

  private fun printMerchants() {
    for ((index, value) in merchants.withIndex()) {
      println("${index + 1}: $value")
    }
  }
}