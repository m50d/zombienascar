package net.homelinux.md401.zombienascar.snippet
import scala.xml.NodeSeq
import net.liftweb.openid.OpenIDUser
import scala.xml.Text

class username {
	def render(): NodeSeq = {
	  Text(OpenIDUser.get.get.getIdentifier())
	}
}