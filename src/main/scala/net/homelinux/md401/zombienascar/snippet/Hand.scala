package net.homelinux.md401.zombienascar.snippet
import scala.xml.NodeSeq
import net.homelinux.md401.zombienascar.backend.Deck
import net.liftweb.http.js.JE.JsRaw
import java.util.UUID
import net.liftweb.http.SHtml
import net.liftweb.http.JsonHandler
import net.liftweb.http.js.JsCmd
import net.liftweb.util.JsonCmd
import net.liftweb.http.js.JE
import net.liftweb.http.js.JsCmds
import scala.xml.Text
import net.liftweb.http.js.JsonCall

object json extends JsonHandler {
  def apply(in: Any): JsCmd = { in match { 
            case JsonCmd("play", _, p: Map[String, UUID], _) => {
              println(p)
                // process the form or whatever 
//                println("Cars = " + urlDecode(p("cars"))) 
//                println("Name = " + urlDecode(p("name"))) 
//                <b>{p}</b> 
            }
            case _ => JsCmd.unitToJsCmd()
        }}
}

class playbutton {
  def render(): NodeSeq = {
    JsCmds.Script(json.jsCmd)  ++ <input type="submit" onclick={Text(json.call("play", JE.JsRaw("window.selected")).toJsCmd)}></input>
  }
}