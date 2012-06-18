package net.homelinux.md401.zombienascar.backend

sealed trait Square {
  def filename: String
}

object BlankSquare extends Square {
  val filename = "blank.png"
}