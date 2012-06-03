package net.homelinux.md401.zombienascar.comet
import net.liftweb.actor.LiftActor
import net.liftweb.http.ListenerManager
import org.openid4java.discovery.Identifier
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener
import scala.xml.Text

object Users extends LiftActor with ListenerManager {
  private var users: Vector[Identifier] = Vector()

  def createUpdate = users

  override def lowPriority = {
    case i: Identifier => users :+= i; updateListeners()
  }
}

class UserList extends CometActor with CometListener {
  private var users: Vector[Identifier] = Vector()
  def registerWith = Users
  override def lowPriority = {
    case v: Vector[Identifier] => {
      users = v;
      reRender();
    }
  }

  override def render = (users map (u => <label><input type="checkbox"></input>{u.getIdentifier}</label>))
}