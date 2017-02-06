package no.knowit.unfiltered

object Server {
  def main(args: Array[String]) {
    unfiltered.jetty.Server.http(8080).plan(new Plans).run()
  }
}


