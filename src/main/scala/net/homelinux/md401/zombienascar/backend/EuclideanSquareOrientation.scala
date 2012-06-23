package net.homelinux.md401.zombienascar.backend

sealed trait EuclideanSquareOrientation {
  def left: EuclideanSquareOrientation
  def right: EuclideanSquareOrientation
  def uTurn: EuclideanSquareOrientation
  def forward(pos: EuclideanSquarePosition): EuclideanSquarePosition
  //XXX: Forward 1/2/3?
  def backward(pos: EuclideanSquarePosition): EuclideanSquarePosition
}

object North extends EuclideanSquareOrientation {
  def left = West
  def right = East
  def uTurn = South
  def forward(p: EuclideanSquarePosition) = p match {
    case EuclideanSquarePosition(x, y) => EuclideanSquarePosition(x, y + 1)
  }
  def backward(p: EuclideanSquarePosition) = p match {
    case EuclideanSquarePosition(x, y) => EuclideanSquarePosition(x, y - 1)
  }
}

object East extends EuclideanSquareOrientation {
  def left = North
  def right = South
  def uTurn = West
  def forward(p: EuclideanSquarePosition) = p match {
    case EuclideanSquarePosition(x, y) => EuclideanSquarePosition(x + 1, y)
  }
  def backward(p: EuclideanSquarePosition) = p match {
    case EuclideanSquarePosition(x, y) => EuclideanSquarePosition(x - 1, y)
  }
}

object South extends EuclideanSquareOrientation {
  def left = East
  def right = West
  def uTurn = North
  def forward(p: EuclideanSquarePosition) = p match {
    case EuclideanSquarePosition(x, y) => EuclideanSquarePosition(x, y - 1)
  }
  def backward(p: EuclideanSquarePosition) = p match {
    case EuclideanSquarePosition(x, y) => EuclideanSquarePosition(x, y + 1)
  }
}

object West extends EuclideanSquareOrientation {
  def left = South
  def right = North
  def uTurn = East
  def forward(p: EuclideanSquarePosition) = p match {
    case EuclideanSquarePosition(x, y) => EuclideanSquarePosition(x-1, y)
  }
  def backward(p: EuclideanSquarePosition) = p match {
    case EuclideanSquarePosition(x, y) => EuclideanSquarePosition(x+1, y)
  }
}