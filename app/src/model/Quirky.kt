package model

import model.strategy.MerchantStrategy

/**
 * This is a quirky merchant.
 * @see model.strategy.QuirkyStrategy
 *
 * @author Renat Kaitmazov
 */

class Quirky(
    id: Int,
    strategy: MerchantStrategy
) : Merchant(id, strategy) {
}