package net.homelinux.md401.zombienascar.backend

sealed trait Card {
  val filename: String
}

object Forward1 extends Card {
  val filename = "forward1.png"
}
object Forward2 extends Card {
  val filename = "forward2.png"
}
object Forward3 extends Card {
  val filename = "forward3.png"
}
object Left extends Card {
  val filename = "left.png"
}
object Right extends Card {
  val filename = "right.png"
}
object Uturn extends Card {
  val filename = "uturn.png"
}
object Back extends Card {
  val filename = "back.png"
}