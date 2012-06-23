package net.homelinux.md401.zombienascar.backend

sealed trait Square {
  def filename: String
  def actOn(c: Car): Car
}

sealed abstract class Conveyor(o: EuclideanSquareOrientation) extends Square {
  def actOn(c: Car) = Car(o.forward(c.position), c.orientation)
}

object BlankSquare extends Square {
  val filename = "blank.png"
  def actOn(c:Car) = c
}

object NorthConveyor extends Conveyor(North) {
  val filename = "northconveyor.png"
}

object SouthConveyor extends Conveyor(South) {
  val filename = "southconveyor.png"
}

object EastConveyor extends Conveyor(East) {
  val filename = "northconveyor.png"
}

object WestConveyor extends Conveyor(West) {
  val filename = "northconveyor.png"
}

object ClockwiseGear extends Square {
  val filename = "northconveyor.png"
  def actOn(c:Car) = Car(c.position, c.orientation.right) 
}

object AnticlockwiseGear extends Square {
  val filename = "northconveyor.png"
  def actOn(c:Car) = Car(c.position, c.orientation.left) 
}