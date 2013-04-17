package controllers

import play.api._
import play.api.mvc._

import scala.concurrent.Future
import play.api.libs._
import play.api.libs.concurrent.Execution.Implicits._

object Stores extends Controller {

  def activeSalesByKey(store: String) = Action {
    val eventualResponse: Future[ws.Response] = {
      signedApiRequest("sales/"+store+"/active.json").get()
    }

    Async {
      eventualResponse map { response =>
        Status(response.status) { response.body }
      }
    }
  }

  private def signedApiRequest(path: String) = {
    val apiKey = Play.maybeApplication flatMap { app =>
      app.configuration.getString("gilt.apikey")
    } getOrElse ""

    ws.WS.url(API_ROOT+path).withQueryString("apikey" -> apiKey)
  }

  private val API_ROOT = "https://api.gilt.com/v1/"

}
