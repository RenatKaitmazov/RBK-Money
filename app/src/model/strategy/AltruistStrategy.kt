package model.strategy

/**
 * Defines a strategy for an altruist merchant.
 * An altruist merchant always cooperates and never cheats.
 *
 * @author Renat Kaitmazov
 */

class AltruistStrategy : MerchantStrategy {

  /*----------* MerchantStrategy implementation *----------*/

  override fun isCheating() = false

  override fun onColleagueMove(hasCheated: Boolean) {
    // Its strategy does not depend on the strategy of a colleague.
  }
}