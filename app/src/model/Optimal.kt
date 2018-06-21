package model

import model.strategy.MerchantStrategy

/**
 * Part of the additional task.
 * @see model.strategy.OptimalStrategy
 *
 * @author Renat Kaitmazov
 */

class Optimal(id: Int, strategy: MerchantStrategy) : Merchant(id, strategy) {
}