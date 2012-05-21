package bootstrap.liftweb

import _root_.net.liftweb.util._
import _root_.net.liftweb.common._
import _root_.net.liftweb.http._
import _root_.net.liftweb.http.provider._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._
import _root_.net.liftweb.mapper.{ DB, ConnectionManager, Schemifier, DefaultConnectionIdentifier, StandardDBVendor }
import _root_.java.sql.{ Connection, DriverManager }
import net.homelinux.md401.zombienascar.snippet.SimpleOpenIdVendor

class Boot {
  def boot {
    LiftRules.addToPackages("net.homelinux.md401.zombienascar")
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"));
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))
    LiftRules.dispatch.append(SimpleOpenIdVendor.dispatchPF)
  }
}
