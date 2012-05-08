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
import _root_.net.homelinux.md401.zombienascar.model._

class Boot {
  def boot {
    if (!DB.jndiJdbcConnAvailable_?) {
      val vendor =
        new StandardDBVendor(classOf[org.postgresql.Driver] getName,
          sys.env("DATABASE_URL"),
          Empty, Empty)
      LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)
      DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }
    // where to search snippet
    LiftRules.addToPackages("net.homelinux.md401.zombienascar")
    Schemifier.schemify(true, Schemifier.infoF _, User)
    // Build SiteMap
    LiftRules.setSiteMap(
        SiteMap(
      Menu("Home") / "index" :: // Simple menu form
        // Menu with special Link
        Menu(Loc("Static", Link(List("static"), true, "/static/index"),
          "Static Content")) ::
        // Menu entries for the User management stuff
        User.sitemap: _*)
    )
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"));
    LiftRules.loggedInTest = Full(() => User.loggedIn_?)
    S.addAround(DB.buildLoanWrapper)
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))
  }

}
