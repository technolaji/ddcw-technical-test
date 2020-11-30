import org.scalamock.scalatest.MockFactory
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.Application
import play.api.i18n.MessagesApi
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.ControllerComponents
import play.api.test.Helpers.stubControllerComponents
import support.UnitSpec

package object controllers {

  trait ControllerTestResources
      extends UnitSpec
      with MockFactory
      with GuiceOneAppPerSuite {

    implicit override lazy val app: Application =
      new GuiceApplicationBuilder().build()

    protected val mockCC: ControllerComponents = stubControllerComponents()
  }

  def getOperationString(operationType: String, amount: Option[Float]): String = {
    s"""{ "operationType" : "$operationType", "amount" : ${amount.orNull}}"""
  }

  def getMessageJson(operationType: String, amount: Option[Float]): JsValue = {
    val rawMsg = "{ \\\"operationType\\\" : \\\"type\\\", \\\"amount\\\" : 0}"
    val message = rawMsg.replace("type", operationType).replace("0", amount.getOrElse("null").toString)
    val messageJson: JsValue = Json.parse(s"""
         |{
         |   "message": "$message"
         |}
    """.stripMargin)
    messageJson
  }
}
