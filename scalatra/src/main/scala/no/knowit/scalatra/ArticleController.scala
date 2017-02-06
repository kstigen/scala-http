package no.knowit.scalatra

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport

class ArticleController extends ScalatraStack with JacksonJsonSupport{

  protected implicit lazy val jsonFormats: Formats = DefaultFormats
  val articleRepository = new ArticleRepository

  before() {
    contentType = formats("json")
  }

  get("/") {
    params.get("query") match {
      case Some(q) => articleRepository.matching(q)
      case None => articleRepository.all
    }
  }

  get("/:id") {
    articleRepository.withId(params("id").toInt)
  }

  post("/") {
    articleRepository.insert(parsedBody.extract[NewArticle])
  }
}
