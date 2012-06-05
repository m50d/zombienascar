package net.homelinux.md401.zombienascar.backend

sealed trait Card {
  val filename: String
}

case class Forward1 extends Card {
  val filename = "forward1.png"
}
case class Forward2 extends Card {
  val filename = "forward2.png"
}
case class Forward3 extends Card {
  val filename = "forward3.png"
}
case class Left extends Card {
  val filename = "left.png"
}
case class Right extends Card {
  val filename = "right.png"
}
case class Uturn extends Card {
  val filename = "uturn.png"
}
case class Back extends Card {
  val filename = "back.png"
}