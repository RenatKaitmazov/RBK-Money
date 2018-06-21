package model.strategy

import java.util.LinkedList
import java.util.Queue

/**
 * Defines a strategy for a quirky merchant.
 * A quirky merchant has the following strategy:
 * - always starts with this sequence of moves -> cooperation, cheating, cooperation, cooperation
 * - if it has been deceived during the sequence of move, then it acts like [RogueStrategy]; if not,
 * it acts like [TrickyStrategy].
 *
 * @author Renat Kaitmazov
 */

class QuirkyStrategy : MerchantStrategy {

  /*----------* Properties *----------*/

  private val initialMoves: Queue<Boolean> = LinkedList()
  private var nextStrategy: MerchantStrategy? = null
  private var hasBeenDeceived = false

  init {
    initialMoves.add(false)
    initialMoves.add(true)
    initialMoves.add(false)
    initialMoves.add(false)
  }

  /*----------* MerchantStrategy implementation *----------*/

  override fun isCheating(): Boolean {
    if (initialMoves.isNotEmpty()) {
      return initialMoves.remove()
    }
    if (nextStrategy == null) {
      nextStrategy = if (hasBeenDeceived) RogueStrategy() else TrickyStrategy()
    }
    return nextStrategy!!.isCheating()
  }

  override fun onColleagueMove(hasCheated: Boolean) {
    if (!hasBeenDeceived && initialMoves.isNotEmpty()) {
      hasBeenDeceived = hasCheated
    }
    nextStrategy?.onColleagueMove(hasCheated)
  }
}