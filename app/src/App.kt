import dataprovider.DealSimulatorProvider
import domain.Generator

/**
 * @author Renat Kaitmazov
 */

fun main(args: Array<String>) {
  val dealsPerYear = Generator.numberInRange(5, 10)
  val dealSimulator = DealSimulatorProvider.newDealSimulator(dealsPerYear)
  val years = 500
  dealSimulator.simulate(years)
}