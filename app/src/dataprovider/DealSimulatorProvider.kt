package dataprovider

import domain.DealMaker
import domain.DealSimulator
import domain.Generator

/**
 * A factory that creates instances of [DealSimulator] class.
 *
 * @author Renat Kaitmazov
 */

object DealSimulatorProvider {

  /*----------* API *----------*/

  /**
   * Creates a new instance of [DealSimulator] class.
   * @return an instance of [DealSimulator].
   */
  fun newDealSimulator(dealsPerYear: Int) = DealSimulator(
      merchantProvider = MerchantProvider,
      dealMaker = DealMaker(generator = Generator),
      amountOfDealsPerYear = dealsPerYear
  )
}