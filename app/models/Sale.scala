package models

import play.api.libs.json._

case class Sale(
  name: String,
  key: String,
  giltUrl: String,
  apiUrl: String
)

object Sale {
  implicit val readSale: Reads[Sale] = new Reads[Sale] {
    def reads(json: JsValue): JsResult[Sale] = {
      for {
        name <- (json \ "name").validate[String]
        key <- (json \ "sale_key").validate[String]
        url <- (json \ "sale_url").validate[String]
        api <- (json \ "sale").validate[String]
      } yield Sale(name, key, url, api)
    }
  }
}
