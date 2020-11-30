package models

import play.api.libs.json._

object OperationType extends Enumeration {
  type OperationType = Value
  val Balance = Value("balance")
  val Deposit = Value("deposit")
  val Withdraw = Value("withdraw")
}

trait OperationTypeJsonProtocol {
  implicit val myEnumReads: Reads[OperationType.Value] = Reads.enumNameReads(OperationType)
  implicit val myEnumWrites = Writes.enumNameWrites
}
