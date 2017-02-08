package no.knowit.unfiltered

import org.json4s.native.Serialization.{read, write}
import unfiltered.directives.Directive
import unfiltered.directives.Directives.{failure, success}
import unfiltered.request._
import unfiltered.response.{JsonContent, MethodNotAllowed, NotFound, Ok, ResponseString}

class Plans extends unfiltered.filter.Plan {

  val articleRepository = new ArticleRepository()
  implicit val formats = org.json4s.DefaultFormats

  def intent = Directive.Intent {
    case req @ Path("/articles")  => req match {
      case GET(_) & Params(NonEmptyQuery(query)) =>
        success(Ok ~> JsonContent ~> ResponseString(write(articleRepository.matching(query))))

      case GET(_)  =>
        success(Ok ~> JsonContent ~> ResponseString(write(articleRepository.all)))

      case POST(_) =>
        success(Ok ~> JsonContent ~> ResponseString(write(articleRepository.insert(read[NewArticle](Body.string(req))))))

      case _ => failure(MethodNotAllowed ~> ResponseString("Supports only GET and POST"))
    }

    case req @ Path(Seg("articles" :: id :: Nil)) => req match {
      case GET(_) =>
        success(Ok ~> JsonContent ~> ResponseString(write(articleRepository.withId(id.toInt))))

      case _ =>
        failure(MethodNotAllowed ~> ResponseString("Supports only GET"))
    }

    case _ => failure(NotFound ~> ResponseString("We could not find what you were looking for"))
  }
}

object NonEmptyQuery extends Params.Extract(
  "query",
  Params.first ~> Params.nonempty
)