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
import net.homelinux.md401.zombienascar.comet.Users
import org.openid4java.discovery.DiscoveryInformation
import org.openid4java.message.AuthRequest
import net.liftweb.common.Empty


trait SimpleOpenIdVendor extends OpenIDVendor { 
  class RealNameFetchingConsumer extends OpenIDConsumer[UserType] {
  beforeAuth = Full({case (di: DiscoveryInformation, ar: AuthRequest) => {
    
  }})
}
  type UserType = Identifier   
  type ConsumerType = RealNameFetchingConsumer
   
  def currentUser = OpenIDUser.is
  def postLogin(id: Box[Identifier],res: VerificationResult): Unit = {
    id match {
      case Full(id) => Users ! id
      case _ => S.error("Failed to authenticate")
    }
    OpenIDUser(id)
  }
  def logUserOut() {
    OpenIDUser.remove   
  }
  def displayUser(in: UserType): NodeSeq = Text("Welcome "+in)
  def createAConsumer = new RealNameFetchingConsumer
}
object SimpleOpenIdVendor extends SimpleOpenIdVendor

class openid {
  def render(xhtml: NodeSeq): NodeSeq = {
    SimpleOpenIdVendor.loginForm
  }
}