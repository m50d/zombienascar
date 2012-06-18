package net.homelinux.md401.zombienascar.backend

sealed trait Square {
  
}

object BlankSquare extends Square {
  val filename = "blank.png"
}