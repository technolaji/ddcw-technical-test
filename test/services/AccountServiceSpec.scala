package services

import support.UnitSpec

class AccountServiceSpec extends UnitSpec {

  private val balanceService = new AccountService

  "Balance Service" should {
    "return balance" in {
      balanceService.balance = 20
      val result = balanceService.showBalance()
      result shouldBe "Your current balance is £20.00"
    }

    "return deposited amount" in {
      balanceService.balance = 10
      val amount = 25
      val result = balanceService.deposit(Some(amount))
      result shouldBe "Your current balance is £35.00"
    }

    "withdraw balance" in {
      balanceService.balance = 50
      val amount = 30
      val result = balanceService.withdraw(Some(amount))
      result shouldBe "Your current balance is £20.00"
    }

    "return original balance when deposit when amount is none" in {
      balanceService.balance = 50
      val result = balanceService.deposit(None)
      result shouldBe "Your current balance is £50.00"
    }

    "return original balance when withdraw when amount is none" in {
      balanceService.balance = 50
      val result = balanceService.withdraw(None)
      result shouldBe "Your current balance is £50.00"
    }

    "return deposited amount when there's two decimal places" in {
      balanceService.balance = 10
      val amount = 25.50.toFloat
      val result = balanceService.deposit(Some(amount))
      result shouldBe "Your current balance is £35.50"
    }

    "return withdrawn amount when there's two decimal places" in {
      balanceService.balance = 50
      val amount = 10.51.toFloat
      val result = balanceService.withdraw(Some(amount))
      result shouldBe "Your current balance is £39.49"
    }
  }
}
