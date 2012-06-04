package net.homelinux.md401.zombienascar.snippet
import scala.xml.NodeSeq
import net.liftweb.openid.OpenIDUser
import scala.xml.Text
import net.liftweb.http.SessionVar
import net.liftweb.common.Box
import net.liftweb.common.Empty

object Username extends SessionVar[String]("Unknown")

class usernamedisplay {
	def render(): NodeSeq = Text(Username.get)
	
}