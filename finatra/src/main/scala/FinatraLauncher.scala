import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import no.knowit.articles.ArticlesController

object FinatraLauncher extends FinatraServer

class FinatraServer extends HttpServer {
  override val defaultFinatraHttpPort: String = ":8080"
  override protected def configureHttp(router: HttpRouter): Unit = {
    router.add[ArticlesController]
  }
}
