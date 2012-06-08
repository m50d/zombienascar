package net.homelinux.md401.zombienascar.comet
import scala.annotation.serializable

import org.openid4java.discovery.Identifier

import net.liftweb.actor.LiftActor
import net.liftweb.http.CometActor
import net.liftweb.http.CometListener
import net.liftweb.http.ListenerManager
import net.liftweb.util.IterableConst.itStringPromotable

object ChatServer extends LiftActor with ListenerManager {
  private var msgs = Vector("Welcome") // private state

  def createUpdate = msgs

  override def lowPriority = {
    case s: String => msgs :+= s; updateListeners()
  }
}

class Lobby extends CometActor with CometListener {
  private var msgs: Vector[String] = Vector()

  def registerWith = ChatServer

  override def lowPriority = {
    case v: Vector[String] => {
      msgs = v;
      reRender();
    }
  }

  override def render = "li *" #> msgs
}
