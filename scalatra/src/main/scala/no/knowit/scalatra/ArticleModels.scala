package no.knowit.scalatra

case class Author(id: Long, name: String)
case class Article(id: Long, title: String, content: String, author:Author)
case class NewArticle(title: String, content: String, authorId: Long)
