package net.homelinux.md401.zombienascar.backend

sealed trait Square {
  def filename: String
  def actOn(c: Car): Car
}

object Square {
  def char2Square(c: Char): Square = c match {
    case '.' => BlankSquare
    case '↑' => NorthConveyor
    case '↓' => SouthConveyor
    case '→' => EastConveyor
    case '←' => WestConveyor
    case '↷' => ClockwiseGear
    case '↶' => AnticlockwiseGear
    case _ => throw new RuntimeException("Unknown tile " + c)
  }
  def string2Row(s: String): IndexedSeq[Square] = s map char2Square
  def board(r: String*): List[IndexedSeq[Square]] = List() ++ (r map string2Row)
}

sealed abstract class Conveyor(o: EuclideanSquareOrientation) extends Square {
  def actOn(c: Car) = Car(o.forward(c.position), c.orientation)
}

object BlankSquare extends Square {
  val filename = "blank.png"
  def actOn(c:Car) = c
}

object NorthConveyor extends Conveyor(North) {
  val filename = "conveyor090.png"
}

object SouthConveyor extends Conveyor(South) {
  val filename = "conveyor270.png"
}

object EastConveyor extends Conveyor(East) {
  val filename = "conveyor180.png"
}

object WestConveyor extends Conveyor(West) {
  val filename = "conveyor000.png"
}

object ClockwiseGear extends Square {
  val filename = "northconveyor.png"
  def actOn(c:Car) = Car(c.position, c.orientation.right) 
}

object AnticlockwiseGear extends Square {
  val filename = "northconveyor.png"
  def actOn(c:Car) = Car(c.position, c.orientation.left) 
}