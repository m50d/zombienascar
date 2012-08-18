package net.homelinux.md401.zombienascar.backend
import scala.xml.NodeSeq
import scala.xml.Text

case class DisplayTile(square: Square, car: Option[Car]) {
  def render: NodeSeq = {
    val carOrNothing : NodeSeq = car match {
      case Some(c) => <div style="position:absolute"><img src="tiles/car.png"></img></div>
      case None => Text("")
    }
    carOrNothing ++ <img src={ "tiles/" + square.filename }/>
  }
}