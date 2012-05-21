package net.homelinux.md401.zombienascar.snippet
import scala.xml.NodeSeq
import net.liftweb.openid.OpenIDVendor
import org.openid4java.discovery.Identifier
import net.liftweb.openid.OpenIDConsumer
import net.liftweb.openid.OpenIDUser
import net.liftweb.common.Box
import org.openid4java.consumer.VerificationResult
import net.liftweb.common.Full
import net.liftweb.http.S
import scala.xml.Text

trait SimpleOpenIdVendor extends OpenIDVendor {   
  type UserType = Identifier   
  type ConsumerType = OpenIDConsumer[UserType]
   
  def currentUser = OpenIDUser.is
  def postLogin(id: Box[Identifier],res: VerificationResult): Unit = {
    id match {
      case Full(id) => S.notice("Welcome "+id)
      case _ => S.error("Failed to authenticate")
    }
    OpenIDUser(id)
  }
  def logUserOut() {
    OpenIDUser.remove   
  }
  def displayUser(in: UserType): NodeSeq = Text("Welcome "+in)
  def createAConsumer = new AnyRef with OpenIDConsumer[UserType]
}
object SimpleOpenIdVendor extends SimpleOpenIdVendor

class openid {
  def render(xhtml: NodeSeq): NodeSeq = {
    SimpleOpenIdVendor.loginForm
  }
}