package net.homelinux.md401.zombienascar.backend
import scala.xml.NodeSeq

class Board {
  val tiles = List.tabulate(12, 12)((_, _) => BlankSquare)
  def render: NodeSeq = {
    <table>{
      tiles map {
        row =>
          <tr>
            {
              row map {
                tile: Square => <td><img src={ tile.filename }/></td>
              }
            }
          </tr>
      }
    }</table>
  }
}