package net.homelinux.md401.zombienascar.snippet
import scala.xml.Text
import net.liftweb.http.SessionVar
import org.openid4java.discovery.Identifier

object Players extends SessionVar[Map[String, Identifier]](Map())

class playerlist {
	def render() = Players.get.keys map {s => <li>{Text(s)}</li>}
}