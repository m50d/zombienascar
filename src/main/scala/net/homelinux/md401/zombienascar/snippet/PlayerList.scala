package net.homelinux.md401.zombienascar.snippet
import scala.xml.Text
import net.liftweb.http.SessionVar
import org.openid4java.discovery.Identifier
import scala.xml.NodeSeq

object Players extends SessionVar[Map[String, Identifier]](Map())

class playerlist {
	def render(): NodeSeq = Players.get.keys.toList map {s => <li>{Text(s)}</li>}
}