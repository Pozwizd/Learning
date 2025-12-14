package org.example.Java._2_Collections.Collections.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Демонстрационный класс, показывающий работу с DelayQueue.[web:239][web:240]
 *
 * DelayQueue — это специализированная реализация BlockingQueue, в которой элементы можно забирать
 * только после истечения заданного для них времени задержки (delay).[web:239][web:241][web:196]
 *
 * Ключевые особенности DelayQueue:[web:239][web:241][web:242][web:196]
 * - Очередь **неограниченная** (unbounded), основана на приоритетной очереди (min‑heap).[web:241][web:244][web:247]
 * - Элементы обязаны реализовывать интерфейс Delayed (методы getDelay и compareTo).[web:239][web:241][web:247]
 * - Головой очереди становится элемент с **наименьшим оставшимся временем задержки**.[web:243][web:196]
 * - Метод take() блокируется, пока у головы очереди не истечёт задержка; пока delay не истёк, элемент «невидим» для извлечения.[web:239][web:241][web:247]
 * - poll() возвращает элемент только если его задержка уже истекла, иначе возвращает null.[web:196][web:247]
 *
 * Типичные сценарии использования:[web:240][web:244][web:245]
 * - Планирование отложенных задач (task scheduler / reminder queue).[web:244][web:252]
 * - Реализация таймаутов, ретраев, временных блокировок и т.п.[web:240][web:245]
 *
 * В этом примере:[web:239]
 * - создаём DelayQueue с элементами Task, каждый из которых имеет имя и задержку в миллисекундах;[web:239]
 * - добавляем задачи с разными задержками;[web:239]
 * - в цикле забираем задачи через take() — они будут возвращаться **по мере истечения задержки**, а не по порядку добавления.[web:239][web:243]
 */
public class DelayQueueExample {

    public static void main(String[] args) throws InterruptedException {
        // Работаем через интерфейс BlockingQueue, указывая реализацию DelayQueue.[web:239][web:241]
        BlockingQueue<DelayedTask> queue = new DelayQueue<>();

        long now = System.currentTimeMillis(); // Фиксируем «текущее время» для удобства расчёта задержки.[web:239]

        // Добавляем несколько задач с разной задержкой относительно текущего времени.[web:239][web:243]
        // Время задержки задаётся в миллисекундах.
        // Чем меньше задержка, тем раньше задача станет доступной для извлечения из очереди.[web:243][web:196]
        queue.put(new DelayedTask("Task 1 (3s delay)", now + 3000)); // выполнится примерно через 3 секунды.[web:239]
        queue.put(new DelayedTask("Task 2 (1s delay)", now + 1000)); // выполнится примерно через 1 секунду.[web:239]
        queue.put(new DelayedTask("Task 3 (2s delay)", now + 2000)); // выполнится примерно через 2 секунды.[web:239]
        queue.put(new DelayedTask("Task 4 (4s delay)", now + 4000)); // выполнится примерно через 4 секунды.[web:239]

        System.out.println("Все задачи добавлены в DelayQueue, ожидание истечения задержек..."); // Пояснительный вывод.[web:239]

        // Забираем элементы через take().[web:239][web:247]
        // take() блокируется до тех пор, пока у головы очереди не истечёт delay.[web:239][web:241]
        // Порядок вывода будет соответствовать возрастанию времени истечения delay, а не порядку add/put.[web:243][web:196]
        while (!queue.isEmpty()) {
            DelayedTask task = queue.take(); // Блокируется, пока delay у ближайшей задачи не станет <= 0.[web:239][web:247]
            long execTime = System.currentTimeMillis();
            System.out.println(execTime + " — выполнена: " + task);
        }

        System.out.println("Все задержанные задачи обработаны, очередь пуста."); // Финальный вывод.[web:239]
    }
}

/**
 * Класс DelayedTask представляет задачу с отложенным временем выполнения для DelayQueue.[web:239][web:245]
 *
 * Реализует интерфейс Delayed, что обязательно для элементов DelayQueue.[web:239][web:241][web:247]
 * Каждый объект хранит:[web:239]
 * - текстовое описание задачи;[web:239]
 * - момент времени (timestamp в миллисекундах), когда задача становится доступной для обработки.[web:239][web:243]
 */
class DelayedTask implements Delayed {

    private final String name;      // Имя/описание задачи.[web:239]
    private final long executeAt;   // Временная метка (millis), когда задержка считается истёкшей.[web:239][web:243]

    /**
     * @param name      описание задачи.[web:239]
     * @param executeAt абсолютное время (System.currentTimeMillis() + delayMs),
     *                  когда задача должна «разблокироваться» в DelayQueue.[web:239][web:243]
     */
    public DelayedTask(String name, long executeAt) {
        this.name = name;
        this.executeAt = executeAt;
    }

    /**
     * Метод getDelay сообщает, сколько времени осталось до «разблокировки» элемента.[web:239][web:241][web:247]
     *
     * @param unit единица измерения (секунды, миллисекунды и т.п.), в которую нужно перевести задержку.[web:239]
     * @return оставшееся время до истечения задержки в указанных единицах; если <= 0, элемент считается готовым к извлечению.[web:239][web:243]
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long diffMillis = executeAt - System.currentTimeMillis(); // Сколько миллисекунд осталось до executeAt.[web:239][web:243]
        return unit.convert(diffMillis, TimeUnit.MILLISECONDS);   // Переводим в нужную единицу.[web:239]
    }

    /**
     * compareTo определяет порядок элементов внутри очереди по времени истечения задержки.[web:239][web:243][web:247]
     *
     * Элементы с меньшим executeAt (истекают раньше) должны идти «раньше» в очереди.[web:243][web:244]
     */
    @Override
    public int compareTo(Delayed other) {
        if (other == this) {
            return 0; // Одинаковые ссылки считаем равными.[web:239]
        }
        if (other instanceof DelayedTask) {
            DelayedTask that = (DelayedTask) other;
            return Long.compare(this.executeAt, that.executeAt); // Сравниваем по времени исполнения.[web:243][web:247]
        }
        // На всякий случай делегируем сравнение через getDelay, если тип другой реализации Delayed.[web:247]
        long diff = this.getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS);
        return Long.compare(diff, 0);
    }

    /**
     * Переопределяем toString для наглядного вывода задачи в лог.[web:239]
     */
    @Override
    public String toString() {
        return "DelayedTask{name='" + name + "', executeAt=" + executeAt + "}";
    }
}
