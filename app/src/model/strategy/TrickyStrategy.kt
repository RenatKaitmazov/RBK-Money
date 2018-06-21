package model.strategy

/**
 * Defines a strategy for a tricky merchant.
 * A tricky merchant always starts with a cooperation but
 * after the first deal repeats the strategy of his colleague.
 *
 * @author Renat Kaitmazov
 */

class TrickyStrategy : MerchantStrategy {

  /*----------* Properties *----------*/

  private var isFirstDeal = true

  /**
   * Keeps track of a colleague's move to repeat it.
   */
  private var colleagueMove = false

  /*----------* MerchantStrategy implementation *----------*/

  override fun isCheating(): Boolean {
    if (isFirstDeal) {
      isFirstDeal = false
      return false
    }
    return colleagueMove
  }

  override fun onColleagueMove(hasCheated: Boolean) {
    colleagueMove = hasCheated
  }
}