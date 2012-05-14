package net.homelinux.md401.zombienascar.snippet
import net.liftweb.http.SHtml
import net.homelinux.md401.zombienascar.comet.ChatServer
import net.liftweb.http.js.JsCmds.SetValById
import scala.xml.NodeSeq
object ChatIn {
  /**
   * The render method in this case returns a function
   * that transforms NodeSeq => NodeSeq.  In this case,
   * the function transforms a form input element by attaching
   * behavior to the input.  The behavior is to send a message
   * to the ChatServer and then returns JavaScript which
   * clears the input.
   */
  def render = {
    System.out.println("In render")
    val tf = SHtml.onSubmit(s => {
      System.out.println("In onsubmit")
    ChatServer ! s
    SetValById("chat_in", "")
  })
  x : NodeSeq => {
    System.out.println("In thing")
    tf(x)
  }
  }
}