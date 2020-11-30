package models

import models.OperationType.OperationType
import play.api.libs.json._

case class Operation(operationType: OperationType, amount: Option[Float])
trait OperationJsonProtocol extends OperationTypeJsonProtocol {
  implicit val operationReads: Reads[Operation] = Json.reads[Operation]
}

case class Message(message: Operation)
trait MessageJsonProtocol extends OperationJsonProtocol {
  implicit val messageReads: Reads[Message] = Json.reads[Message]
}