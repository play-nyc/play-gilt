package controllers

import play.api._
import play.api.mvc._

import scala.concurrent.Future
import play.api.libs._
import play.api.libs.concurrent.Execution.Implicits._

object Stores extends Controller {


  def activeSalesByKey(store: String) = Action {
    val eventualResponse: Future[ws.Response] = {
      ws.WS.url(API_ROOT+"sales/"+store+"/active.json").get()
    }

    Async {
      eventualResponse map { response =>
        Logger.info("API Response: %s %s".format(response.status, response.statusText))
        Status(response.status) { response.body }
      }
    }
  }

  private val API_ROOT = "https://api.gilt.com/v1/"

}
