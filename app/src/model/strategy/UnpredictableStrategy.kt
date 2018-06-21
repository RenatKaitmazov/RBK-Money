package model.strategy

import domain.Generator

/**
 * This type of merchant is unpredictable.
 *
 * @author Renat Kaitmazov
 */

class UnpredictableStrategy(private val generator: Generator) : MerchantStrategy {

  /*----------* MerchantStrategy implementation *----------*/

  override fun isCheating() = generator.boolean()

  override fun onColleagueMove(hasCheated: Boolean) {
    // Its strategy does not depend on the strategy of a colleague.
  }
}