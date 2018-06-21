package model.strategy

/**
 * Each type of merchant has its own strategy on how to make deals
 * with other merchants.
 * This interface abstracts away the concrete implementation of each
 * strategy.
 *
 * @author Renat Kaitmazov
 */

interface MerchantStrategy {
  /**
   * Checks to see if a merchant is cheating or not.
   * @return true if the merchant is cheating, false otherwise.
   */
  fun isCheating(): Boolean

  /**
   * Some merchants' strategy depends upon whether a colleague cheated or not
   * when making a deal.
   * This method should be called when making a deal so that a merchant could
   * adjust his further strategy.
   * @param hasCheated true means that the colleague cooperated, false means that
   * the colleague cheated.
   */
  fun onColleagueMove(hasCheated: Boolean)
}