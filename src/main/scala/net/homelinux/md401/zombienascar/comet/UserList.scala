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

  override def render = (users map (u => {
//    val list = new Discovery().discover(u)
//    val manager = new ConsumerManager()
//    val information = manager.associate(list);
    <label>{SHtml.checkbox(false, x=> x)} {u.getIdentifier}</label>}))
}