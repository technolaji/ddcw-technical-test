package services

import javax.inject.Inject
import models.{Operation, OperationJsonProtocol, OperationType}
import play.api.libs.json._

class MessageService @Inject()(accountService: AccountService)
    extends OperationJsonProtocol {

  def redirectToOperation(message: String): Either[JsError, String] = {
    val operationJson: JsValue = Json.parse(message)
    val operationResult: JsResult[Operation] =
      Json.fromJson[Operation](operationJson)
    operationResult match {
      case JsSuccess(operation: Operation, path: JsPath) => {
        operation match {
          case Operation(OperationType.Balance, _) =>
            Right(accountService.showBalance())
          case Operation(OperationType.Deposit, amount) =>
            Right(accountService.deposit(amount))
          case Operation(OperationType.Withdraw, amount) =>
           Right(accountService.withdraw(amount))
        }
      }

      case error @ JsError(_) =>
        println("Errors: " + JsError.toJson(error).toString())
        Left(error)
    }
  }
}
