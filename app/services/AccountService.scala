package services

class AccountService {

  var balance: Float = 0
  val formatPrefix = "Your current balance is"

  def showBalance(): String = {
    formatResult
  }

  private def round(amount: Float): Float = {
    (Math.round(amount * 100.0) / 100.0).floatValue()
  }

  def deposit(amount: Option[Float]): String = {
    amount.fold(formatResult)(x => {
      balance = round(balance + round(x))
      formatResult
    })
  }

  def withdraw(amount: Option[Float]): String = {
    amount.fold(formatResult)(x => {
      balance = round(balance - round(x))
      formatResult
    })
  }

  private def formatResult: String = {
    val formatter = java.text.NumberFormat.getCurrencyInstance
    s"$formatPrefix ${formatter.format(balance)}"
  }
}
