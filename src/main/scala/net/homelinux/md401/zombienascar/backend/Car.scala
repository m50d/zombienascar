package net.homelinux.md401.zombienascar.backend

case class Car(position: EuclideanSquarePosition, orientation: EuclideanSquareOrientation) {
  def move(c: Card) = {
    c match {
      case Forward1 => Car(orientation.forward(position), orientation)
      case Forward2 => Car(orientation.forward(orientation.forward(position)), orientation)
      case Forward3 => Car(orientation.forward(orientation.forward(orientation.forward(position))), orientation)
      case Back => Car(orientation.backward(position), orientation)
      case Left => Car(position, orientation.left)
      case Right => Car(position, orientation.right)
      case Uturn => Car(position, orientation.uTurn)
    }
  }
}