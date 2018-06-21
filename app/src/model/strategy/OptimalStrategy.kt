package model.strategy

/**
 * Works like [TrickyStrategy] but starts with cheating instead of cooperation.
 *
 * @author Renat Kaitmazov
 */

class OptimalStrategy : MerchantStrategy {

  /*----------* Properties *----------*/

  private var isCheating = true

  /*----------* MerchantStrategy implementation *----------*/

  override fun isCheating(): Boolean {
    return isCheating
  }

  override fun onColleagueMove(hasCheated: Boolean) {
    isCheating = hasCheated
  }
}