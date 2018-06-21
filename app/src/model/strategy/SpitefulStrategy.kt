package model.strategy

/**
 * Defines a strategy for a spiteful merchant.
 * Initially it starts with a cooperation and continues cooperating
 * until it is deceived after which it always cheats.
 *
 * @author Renat Kaitmazov
 */

class SpitefulStrategy : MerchantStrategy {

  /*----------* Properties *----------*/

  private var hasBeenDeceived = false
  private var isFirstDeal = true

  /*----------* MerchantStrategy implementation *----------*/

  override fun isCheating(): Boolean {
    if (isFirstDeal) {
      isFirstDeal = false
      return false
    }
    return hasBeenDeceived
  }

  override fun onColleagueMove(hasCheated: Boolean) {
    if (hasBeenDeceived) {
      return
    }
    hasBeenDeceived = hasCheated
  }
}