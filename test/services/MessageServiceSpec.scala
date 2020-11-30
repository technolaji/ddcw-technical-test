package services

import org.scalamock.scalatest.MockFactory
import play.api.libs.json.JsError
import support.UnitSpec

class MessageServiceSpec extends UnitSpec with MockFactory {

  private val mockAccountService = mock[AccountService]
  private val messageService = new MessageService(mockAccountService)

  private def getOperationString(operationType: String,
                                 amount: Option[Float]): String = {
    s"""{ "operationType" : "$operationType", "amount" : ${amount.orNull}}"""
  }

  "MessageService" should {

    "parse json successfully when operationType is balance" in {

      val value = "Your account is 20.00"

      val operationString = getOperationString("balance", None)
      (mockAccountService.showBalance _).expects().returns(value)
      val Right(response) = messageService.redirectToOperation(operationString)

      response shouldBe value
    }
    "parse json successfully when operationType is withdraw" in {

      val value = "Your account is 20.00"

      val amount = Some(40.toFloat)
      val operationString = getOperationString("withdraw", amount)
      (mockAccountService.withdraw _).expects(amount).returns(value)
      val Right(response) = messageService.redirectToOperation(operationString)

      response shouldBe value
    }

    "parse json successfully when operationType is deposit" in {

      val value = "Your account is 20.00"

      val amount = Some(40.toFloat)
      val operationString = getOperationString("deposit", amount)
      (mockAccountService.deposit _).expects(amount).returns(value)
      val Right(response) = messageService.redirectToOperation(operationString)

      response shouldBe value
    }

    "return json error when operationType is invalid" in {

      val value = "Your account is 20.00"

      val operationString = getOperationString("invalid", None)
      val Left(response): Either[JsError, String] = messageService.redirectToOperation(operationString)
      response shouldBe a[JsError]
    }

  }
}
