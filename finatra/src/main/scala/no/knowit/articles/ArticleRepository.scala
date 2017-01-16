package no.knowit.articles

import com.google.inject.Singleton

@Singleton
class ArticleRepository {
  def all = TestData.articles
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
