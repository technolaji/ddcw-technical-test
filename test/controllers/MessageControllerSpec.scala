package controllers

import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._
import services.MessageService

class MessageControllerSpec extends ControllerTestResources {

  private val mockMessageService = mock[MessageService]
  private val messageController = new MessageController(mockMessageService, mockCC)

  "MessageController POST /print" should {

    "return a response that indicates message received was printed" in {
      (mockMessageService.printToTerminal(_: String)).expects(*).returns(s"Printed '$message1' to terminal")

      val request = messageController.printMessage.apply(FakeRequest().withBody(messageJson))

      status(request) shouldBe OK
      contentAsString(request) shouldBe s"Printed '$message1' to terminal"
    }

  }
}
