package bankSystem;
// Класс для депозитного расчётного счёта, наследуемый от BankAccount
// Из этого счета нельзя снимать деньги в течение месяца после последнего внесения

public class DepositAccount extends BankAccount
{
    private long lastDepositTime;// Время последнего внесения

    // Конструктор, который принимает начальный баланс
    public DepositAccount(double initialBalance) {
        super(initialBalance);// Вызываем конструктор базового класса
        this.lastDepositTime = System.currentTimeMillis();// Устанавливаем текущее время как время последнего внесения
    }

    // Переопределяем метод для внесения денег на счёт
    @Override
    public void depositToAccount(double count) {
        super.depositToAccount(count);
        this.lastDepositTime = System.currentTimeMillis();// Обновляем время последнего внесения
    }

    // Переопределяем метод для снятия денег со счёта
    @Override
    public void withdrawalFromAccount(double count) {
        long currentTime = System.currentTimeMillis();// Получаем текущее время
        long elapsedTime = currentTime - lastDepositTime;// Сколько времени прошло с последнего внесения

        if(elapsedTime >= 30 * 24 * 60 * 60 * 1000L){// 30 дней в миллисекундах, Это выражение означает,что месяц имеет 30 дней,
            // каждый день по 24 часа, каждый час по 60 минут, каждая минута по 60 секунд,и каждый секунд — 1000 миллисекунд.
            // Мы сравниваем elapsedTime с этой константой, чтобы определить, прошел ли месяц:

            super.withdrawalFromAccount(count); // Если да, вызываем метод снятия денег из базового класса
        }
        else
        {
            System.out.println("Вы не можете снимать деньги в течение месяца после последнего внесения."); // Иначе сообщение об ошибке
        }
    }
}
