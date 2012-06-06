package net.homelinux.md401.zombienascar.backend

object Deck { //TODO: make this an actor
	val fullDeck: List[Card] = 
	 List((Uturn(), 6), (Left(), 18), (Right(), 18), (Back(), 6), (Forward1(), 18), (Forward2(), 12), (Forward3(), 6)
	     ) flatMap {
	  case (c: Card, count: Int) => List.make(count, c)
	}
	var currentDeck = util.Random.shuffle(fullDeck)
	def hand(size: Int) = {
		val (a, b) = currentDeck.splitAt(9)
		currentDeck = b
		a
	}
}