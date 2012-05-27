package net.homelinux.md401.zombienascar.comet
import scala.annotation.serializable

import org.openid4java.discovery.Identifier

import net.liftweb.actor.LiftActor
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener
import net.liftweb.http.ListenerManager
import net.liftweb.util.IterableConst.itStringPromotable

object UserList extends LiftActor with ListenerManager {
  private var users: Vector[Identifier] = Vector()
  
  def createUpdate = users
  
  override def lowPriority = {
    case i: Identifier => users :+= i; updateListeners()
  }
}

object ChatServer extends LiftActor with ListenerManager {
  private var msgs = Vector("Welcome") // private state

  def createUpdate = msgs

  override def lowPriority = {
    case s: String => msgs :+= s; updateListeners()
  }
}

/**
 * The screen real estate on the browser will be represented
 * by this component.  When the component changes on the server
 * the changes are automatically reflected in the browser.
 */
class Lobby  extends CometActor with CometListener {
  private var msgs: Vector[String] = Vector() // private state

  /**
   * When the component is instantiated, register as
   * a listener with the ChatServer
   */
  def registerWith = ChatServer

  /**
   * The CometActor is an Actor, so it processes messages.
   * In this case, we're listening for Vector[String],
   * and when we get one, update our private state
   * and reRender() the component.  reRender() will
   * cause changes to be sent to the browser.
   */
  override def lowPriority = {
    case v: Vector[String] => {
      msgs = v;
      System.out.println(msgs)
      reRender();
    }
  }

  /**
   * Put the messages in the li elements and clear
   * any elements that have the clearable class.
   */
  override def render = "li *" #> msgs
}
