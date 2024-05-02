package bankSystem;

public class CardAccount extends BankAccount
{
    // Конструктор, который принимает начальный баланс
    public CardAccount(double initialBalance) {
        super(initialBalance);
    }
    // Переопределяем метод для внесения денег на счёт
    @Override
    public void withdrawalFromAccount(double count) {
        double commission = (count /100);
        if(balance > (count + commission)){
            super.withdrawalFromAccount(count); //  вызываем метод снятия денег из базового класса
            balance -= commission; // Снятие комиссии
            System.out.println("За снятие наличных с карточного счета была списана комиссия в размере " + commission + " рублей");
        }
        else
        {
            System.out.println("На вашем счете недостаточно денежных средств для выполнения данной операции."); // Иначе сообщение об ошибке
        }
    }
}
