package no.knowit.scalatra

class ArticleRepository {

  def all: Iterable[Article] = TestData.articles.values
  def matching(query: String): Iterable[Article] = all
  def withId(id: Int): Option[Article] = {
    TestData.articles.get(id)
  }

  def insert(newArticle: NewArticle): Article = {
    val id = TestData.articles.keys.max + 1
    val article = Article(id, newArticle.title, newArticle.content, TestData.author)
    TestData.articles.put(id, article)
    article
  }

  private object TestData {
    val author = Author(1, "Donald Duck")
    val articles = scala.collection.mutable.Map(
      1 ->  Article(1, "Article 1", "Content", author),
      2 -> Article(2, "Article 2", "Content", author))
  }
}