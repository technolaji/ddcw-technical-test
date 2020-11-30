package controllers

import play.api.libs.json.JsError
import play.api.test.Helpers._
import play.api.test._
import services.{AccountService, MessageService}

class MessageControllerSpec extends ControllerTestResources {

  private val mockMessageService = mock[MessageService]
  private val mockAccountService = mock[AccountService]
  private val messageController =
    new MessageController(mockMessageService, mockAccountService, mockCC)

  "MessageController POST /print" should {

    "return correct balance" in {
      val result = "The account balance is 20.00"
      val operationString = getOperationString("balance", None)
      val testJson = getMessageJson("balance", None)

      (mockMessageService.redirectToOperation _)
        .expects(operationString)
        .returns(Right(result))

      val request =
        messageController.printMessage.apply(FakeRequest().withBody(testJson))

      status(request) shouldBe OK
      contentAsString(request) shouldBe result
    }

    "deposit amount and return balance" in {
      val result = "The account balance is 15.00"
      val amount = Some(15.toFloat)
      val operationString = getOperationString("deposit", amount)
      val testJson = getMessageJson("deposit", amount)

      (mockMessageService.redirectToOperation _)
        .expects(operationString)
        .returns(Right(result))

      val request =
        messageController.printMessage.apply(FakeRequest().withBody(testJson))

      status(request) shouldBe OK
      contentAsString(request) shouldBe result
    }

    "withdraw amount and return balance" in {
      val result = "The account balance is 5.00"
      val amount = Some(5.toFloat)
      val operationString = getOperationString("withdraw", amount)
      val testJson = getMessageJson("withdraw", amount)

      (mockMessageService.redirectToOperation _)
        .expects(operationString)
        .returns(Right(result))

      val request =
        messageController.printMessage.apply(FakeRequest().withBody(testJson))

      status(request) shouldBe OK
      contentAsString(request) shouldBe result
    }

    "return errors when json is invalid" in {
      val amount = Some(5.toFloat)
      val operationString = getOperationString("invalid", amount)
      val testJson = getMessageJson("invalid", amount)

      (mockMessageService.redirectToOperation _)
        .expects(operationString)
        .returns(Left(JsError("invalid json")))

      val request =
        messageController.printMessage.apply(FakeRequest().withBody(testJson))

      status(request) shouldBe BAD_REQUEST
    }
  }

}