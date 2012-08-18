package net.homelinux.md401.zombienascar.snippet
import scala.xml.NodeSeq
import net.homelinux.md401.zombienascar.backend.BlankSquare
import net.homelinux.md401.zombienascar.backend.Square

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