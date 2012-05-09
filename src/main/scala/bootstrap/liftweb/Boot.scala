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

class Boot {
  def boot {
    LiftRules.addToPackages("net.homelinux.md401.zombienascar")
//    Schemifier.schemify(true, Schemifier.infoF _, User)
    // Build SiteMap
    LiftRules.setSiteMap(SiteMap(Menu("Home") / "index"))
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"));
    S.addAround(DB.buildLoanWrapper)
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    if (!DB.jndiJdbcConnAvailable_?) {
    	val vendor =
    			new StandardDBVendor(classOf[org.postgresql.Driver] getName,
    					sys.env("DATABASE_URL"),
    					Empty, Empty)
    	LiftRules.unloadHooks.append(vendor.closeAllConnections_! _)
    	DB.defineConnectionManager(DefaultConnectionIdentifier, vendor)
    }
  }

}
