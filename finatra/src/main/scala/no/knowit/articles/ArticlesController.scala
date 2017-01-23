package no.knowit.articles

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller

class ArticlesController @Inject()(articleRepository: ArticleRepository) extends Controller {

  get("/articles") { request: Request =>
    articleRepository.all
  }

  // Default behaviour if the request does not match the expected input is to give a 500 Internal Server Error
  // Why isn't the default to give a 400 Bad Request?
  get("/search") { request: SearchArticlesRequest =>
    articleRepository.search(request)
  }

//  get("/articles/:id") {getRequest: Request =>
//    // Would assume the else-block would be run if id is not an integer. However, a string that cannot be parsed to an integer is returned as a 0.
//    val articleId = getRequest.params.getIntOrElse("id", throw new IllegalArgumentException("Incorrect input"))
//    articleRepository.withId(articleId).getOrElse(response.notFound().jsonError(s"Article with id $articleId not found"))
//  }

  get("/articles/:id") {request: Request =>
    val id = request.getIntParam("id")
    id > 0 match {
      case false => response.badRequest().jsonError("Param id must be (a) digit(s)")
      case true => articleRepository.withId(id).getOrElse(response.notFound().jsonError(s"Article with id $id not found"))
    }
  }

  post("/articles") { postRequest: NewArticle =>
    "Hei"
  }

}
