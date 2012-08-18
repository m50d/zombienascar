package net.homelinux.md401.zombienascar.backend
import scala.xml.NodeSeq

case class DisplayTile(square: Square, car: Option[Car]) {
  def render: NodeSeq = <img src={ "tiles/" + square.filename }/> ++ car flatMap {_ => <div style="position:absolute">CAR</div>}
}