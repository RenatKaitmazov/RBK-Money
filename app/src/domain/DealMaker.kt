package domain

import model.Merchant

/**
 * This class allows merchants to make deals.
 * It contains the logic of how to make a deal.
 *
 * @author Renat Kaitmazov
 */
class DealMaker(private val generator: Generator) {

  companion object {

    /*----------* Constants *----------*/

    /**
     * The level of certainty when making a deal.
     * Measured in percentages.
     */
    private const val DEAL_CERTAINTY_LEVEL = 95

    /**
     * The minimum possible percent.
     */
    private const val MIN_PERCENT = 0

    /**
     * The maximum possible percent.
     */
    private const val MAX_PERCENT = 100

    /**
     * The amount of coins a merchant gets if he cooperates but his colleague cheats.
     */
    private const val ONE_COINS = 1

    /**
     * The amount of coins both merchants get if they both cheat.
     */
    private const val TWO_COINS = 2

    /**
     * The amount of coins both merchants get if they cooperate when making a deal.
     */
    private const val FOUR_COINS = 4

    /**
     * The amount of coins a merchant gets if he cheats but his colleague cooperates.
     */
    private const val FIVE_COINS = 5
  }

  /*----------* API *----------*/

  fun makeDeal(merchant1: Merchant, merchant2: Merchant) {
    var isFirstMerchantCheating = merchant1.isCheating()
    if (didErrorMakingDeal()) {
      // The first merchant made an error. Reverse his move.
      isFirstMerchantCheating = !isFirstMerchantCheating
    }
    // The second merchant needs to know what move has been made by his colleague.
    merchant2.onColleagueMove(isFirstMerchantCheating, merchant1)
    // And makes his own move.
    var isSecondMerchantCheating = merchant2.isCheating()
    if (didErrorMakingDeal()) {
      // The second merchant made an error. Reverse his move.
      isSecondMerchantCheating = !isSecondMerchantCheating
    }

    if (isFirstMerchantCheating && isSecondMerchantCheating) {
      // Both cheated. Each merchant earns two coins.
      merchant1.lastYearEarnedCoins += TWO_COINS
      merchant2.lastYearEarnedCoins += TWO_COINS
    } else if (isFirstMerchantCheating && !isSecondMerchantCheating) {
      // Only the first merchant is cheating. He gets 5 coins while his colleague gets only one.
      merchant1.lastYearEarnedCoins += FIVE_COINS
      merchant2.lastYearEarnedCoins += ONE_COINS
    } else if (!isFirstMerchantCheating && isSecondMerchantCheating) {
      // Only the second merchant is cheating. He gets 5 coins while his colleague gets only one.
      merchant1.lastYearEarnedCoins += ONE_COINS
      merchant2.lastYearEarnedCoins += FIVE_COINS
    } else {
      // Both cooperated. Each earns four coins.
      merchant1.lastYearEarnedCoins += FOUR_COINS
      merchant2.lastYearEarnedCoins += FOUR_COINS
    }
  }

  /*----------* Helper methods *----------*/

  /**
   * There is always a 5% probability of making an error when making a deal
   * for any merchant type.
   * The method randomly calculates the probability.
   * If an error does occur, then the behavior of a merchant is changed to the opposite.
   * @return true if an error occurs, false otherwise.
   */
  private fun didErrorMakingDeal(): Boolean {
    // Generates a random number in the range of [0, 100].
    val randomCertaintyLevel = generator.numberInRange(MIN_PERCENT, MAX_PERCENT)
    return randomCertaintyLevel > DEAL_CERTAINTY_LEVEL
  }
}