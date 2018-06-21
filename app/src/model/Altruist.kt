package model

import model.strategy.MerchantStrategy

/**
 * This type of merchant always cooperates.
 *
 * @author Renat Kaitmazov
 */

class Altruist(
    id: Int,
    strategy: MerchantStrategy
) : Merchant(id, strategy) {
}