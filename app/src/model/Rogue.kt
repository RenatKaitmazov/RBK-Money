package model

import model.strategy.MerchantStrategy

/**
 * This type of merchant always cheats.
 *
 * @author Renat Kaitmazov
 */

class Rogue(
    id: Int,
    strategy: MerchantStrategy
) : Merchant(id, strategy) {
}