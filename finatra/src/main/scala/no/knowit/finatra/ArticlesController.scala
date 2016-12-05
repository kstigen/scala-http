package no.knowit.finatra

import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class ArticlesController extends Controller {

  get("/") { getRequest: Request =>
    "Hello Finatra"
  }
}
