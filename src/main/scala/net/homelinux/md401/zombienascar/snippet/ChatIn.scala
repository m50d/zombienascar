package net.homelinux.md401.zombienascar.snippet
import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmds.SetValById
import net.liftweb.util.Helpers._
import scala.xml.NodeSeq
import net.liftweb.http.js.JsCmd
import net.homelinux.md401.zombienascar.comet.ChatServer

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
    var msg : String = ""
    def update(): JsCmd = {
    		ChatServer ! msg
    SetValById("chat_in", "")
      }
    "id=chat_in" #> SHtml.ajaxText(msg, msg = _) & 
    "id=chat_submit" #> SHtml.ajaxButton("Say", update: () => JsCmd)
}}