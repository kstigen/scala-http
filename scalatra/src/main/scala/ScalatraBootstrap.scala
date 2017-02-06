import javax.servlet.ServletContext

import no.knowit.scalatra._
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{DefaultServlet, ServletContextHandler}
import org.scalatra._
import org.scalatra.servlet.ScalatraListener

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new ArticleController, "/articles")
  }
}

object ScalatraServer {
  def main(args: Array[String]) {
    val port = if(System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080

    val servletContext = new ServletContextHandler
    servletContext.setContextPath("/")
    servletContext.addEventListener(new ScalatraListener)
    servletContext.addServlet(classOf[DefaultServlet], "/")
    servletContext.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false")

    val server = new Server(port)

    server.setHandler(servletContext)

    server.start()
    server.join()
  }
}
