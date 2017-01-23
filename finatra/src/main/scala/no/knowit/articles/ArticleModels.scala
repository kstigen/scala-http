package no.knowit.articles

import com.twitter.finatra.request.QueryParam
import com.twitter.finatra.validation.{ValidationResult, MethodValidation, Size, Max}

case class Author(id: Long, name: String)
case class Article(id: Long, title: String, content: String, author:Author)
case class NewArticle(
  @Size(min = 2, max = 20) title: String,
  @Size(min = 2, max = 100) content: String,
  authorId: Long) {

  @MethodValidation
  def checkArticle = {
    ValidationResult.validate(title != "Forbidden", "Illegal title")
  }
}

case class SearchArticlesRequest(
  @QueryParam query: String,
  @QueryParam page: Int = 1,
  @Max(50) @QueryParam pageSize: Int = 10)
