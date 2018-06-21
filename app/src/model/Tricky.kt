package model

import model.strategy.MerchantStrategy

/**
 * This is tricky merchant.
 * @see model.strategy.TrickyStrategy
 *
 * @author Renat Kaitmazov
 */

class Tricky(
    id:Int,
    strategy: MerchantStrategy
) : Merchant(id, strategy) {
}