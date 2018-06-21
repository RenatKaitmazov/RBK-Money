package model.strategy

/**
 * Defines a strategy for a rogue merchant.
 * A rogue merchant always cheats and never cooperates.
 *
 * @author Renat Kaitmazov
 */

class RogueStrategy : MerchantStrategy {

  /*----------* MerchantStrategy implementation *----------*/

  override fun isCheating() = true

  override fun onColleagueMove(hasCheated: Boolean) {
    // Its strategy does not depend on the strategy of a colleague.
  }
}