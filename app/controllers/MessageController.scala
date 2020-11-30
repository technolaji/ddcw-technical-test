package controllers

import javax.inject._
import play.api.libs.json.{JsValue, Json, _}
import play.api.mvc._
import services.{AccountService, MessageService}

@Singleton
class MessageController @Inject()(messageService: MessageService,
                                  accountService: AccountService,
                                  cc: ControllerComponents)
    extends AbstractController(cc) {
  val printMessage: Action[JsValue] = Action(parse.json) { implicit request =>
    println(request.body)
    val result =
      messageService.redirectToOperation((request.body \ "message").as[String])
    result match {
      case Left(value) =>
        BadRequest(Json.obj("message" -> JsError.toJson(value)))
      case Right(result: String) =>
        Ok(result)
    }
  }
}
