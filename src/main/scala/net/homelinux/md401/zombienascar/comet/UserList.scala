package net.homelinux.md401.zombienascar.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import org.openid4java.discovery.Identifier
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener
import scala.xml.Text
import org.openid4java.discovery.Discovery
import org.openid4java.discovery.DiscoveryInformation
import org.openid4java.consumer.ConsumerManager
import net.liftweb.http.SHtml
import net.homelinux.md401.zombienascar.snippet.Players
import net.liftweb.http.S
import net.liftweb.http.js.JsCmd
import net.liftweb.http.js.JsCmds

object Users extends LiftActor with ListenerManager {
  private var users: Map[String, Identifier] = Map()
  def createUpdate = users

  override def lowPriority = {
    case (i: Identifier, name: String) => users = users.updated(name, i); updateListeners()
  }
}

class UserList extends CometActor with CometListener {
  private var users: Map[String, Identifier] = Map()
  def registerWith = Users
  override def lowPriority = {
    case m: Map[String, Identifier] => {
      users = m;
      reRender();
    }
  }

  override def render = {
    var selectedUsers: List[String] = Nil
    val userCheckboxes = users.keys.toList map (u => {
      <label>{ SHtml.checkbox(false, if (_) selectedUsers :+= u else selectedUsers = selectedUsers.remove(x => x.equals(u))) } { u }</label>
    })
    val submit = SHtml.ajaxSubmit("Play", () => {
      Players(users.filterKeys(selectedUsers contains _))
      JsCmds.RedirectTo("/game")
    })
    userCheckboxes ++ submit
  }
}