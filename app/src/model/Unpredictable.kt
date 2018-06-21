package model

import model.strategy.MerchantStrategy

/**
 *
 * @author Renat Kaitmazov
 */

class Unpredictable(
    id: Int,
    strategy: MerchantStrategy
) : Merchant(id, strategy) {

}