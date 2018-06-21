package model

import model.strategy.MerchantStrategy

/**
 * This is a spiteful merchant.
 * @see model.strategy.SpitefulStrategy
 *
 * @author Renat Kaitmazov
 */

class Spiteful(
    id: Int,
    strategy: MerchantStrategy
) : Merchant(id, strategy) {
}