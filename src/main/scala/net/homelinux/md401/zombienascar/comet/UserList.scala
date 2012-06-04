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

object Users extends LiftActor with ListenerManager {
  private var users: Map[String, Identifier] = Map()
  def createUpdate = users

  override def lowPriority = {
    case (i: Identifier, name: String) => users = users.updated(name, i); updateListeners()
  }
}

class UserList extends CometActor with CometListener {
  private var users: List[Identifier] = List()
  def registerWith = Users
  override def lowPriority = {
    case m: Map[String, Identifier] => {
      users = m.values.toList;
      reRender();
    }
  }

  override def render = (users map (u => {
//    val list = new Discovery().discover(u)
//    val manager = new ConsumerManager()
//    val information = manager.associate(list);
    <label>{SHtml.checkbox(false, x=> x)} {u.getIdentifier}</label>}))
}