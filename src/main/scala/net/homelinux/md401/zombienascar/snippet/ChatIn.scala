package net.homelinux.md401.zombienascar.snippet
import net.liftweb.http.SHtml
import net.homelinux.md401.zombienascar.comet.ChatServer
import net.liftweb.http.js.JsCmds.SetValById
object ChatIn {
  /**
   * The render method in this case returns a function
   * that transforms NodeSeq => NodeSeq.  In this case,
   * the function transforms a form input element by attaching
   * behavior to the input.  The behavior is to send a message
   * to the ChatServer and then returns JavaScript which
   * clears the input.
   */
  def render = SHtml.onSubmit(s => {
    ChatServer ! s
    SetValById("chat_in", "")
  })
}