package net.homelinux.md401.zombienascar.backend

sealed trait Square {
  def filename: String
}

object BlankSquare extends Square {
  val filename = "blank.png"
}

object NorthConveyor extends Square {
  val filename = "northconveyor.png"
}

object SouthConveyor extends Square {
  val filename = "northconveyor.png"
}

object EastConveyor extends Square {
  val filename = "northconveyor.png"
}

object WestConveyor extends Square {
  val filename = "northconveyor.png"
}

object ClockwiseGear extends Square {
  val filename = "northconveyor.png"
}

object AnticlockwiseGear extends Square {
  val filename = "northconveyor.png"
}