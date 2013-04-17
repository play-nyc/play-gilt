package test

import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

class StoresSpec extends Specification {

  "Stores#activeSalesByKey" should {

    "be successful" in {
      val res = controllers.Stores.activeSalesByKey("women")(FakeRequest())
      status(res) must equalTo(OK)
    }

  }
}
