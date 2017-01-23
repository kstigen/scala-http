package no.knowit.articles

import com.google.inject.Singleton

@Singleton
class ArticleRepository {
  def search(request: SearchArticlesRequest) = TestData.articles.values

  def all = TestData.articles.values
  def withId(id: Int): Option[Article] = {
    TestData.articles.get(id)
  }

  private object TestData {
    val author = Author(1, "Donald Duck")
    val articles = Map(
      1 ->  Article(1, "Article 1", "Content", author),
      2 -> Article(2, "Article 2", "Content", author))
  }
}
