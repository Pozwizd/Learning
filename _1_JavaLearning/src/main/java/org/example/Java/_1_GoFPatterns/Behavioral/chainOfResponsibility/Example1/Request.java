package org.example.Java._1_GoFPatterns.Behavioral.chainOfResponsibility.Example1;

/**
 * Класс {@code Request} представляет собой объект запроса, который передается
 * по цепочке обработчиков. Он содержит флаги, указывающие на необходимость
 * проверки обновлений и перезагрузки системы.
 * Этот класс является частью паттерна "Цепочка обязанностей", инкапсулируя
 * данные, которые должны быть обработаны.
 */
public class Request {
    /**
     * Флаг, указывающий, требуется ли проверка обновлений.
     */
    private boolean checkUpdates;
    /**
     * Флаг, указывающий, требуется ли перезагрузка после установки обновлений.
     */
    private boolean restartRequired;

    /**
     * Конструктор для создания нового объекта запроса.
     *
     * @param checkUpdates    Истина, если требуется проверка обновлений.
     * @param restartRequired Истина, если требуется перезагрузка.
     */
    public Request(boolean checkUpdates, boolean restartRequired) {
        this.checkUpdates = checkUpdates;
        this.restartRequired = restartRequired;
    }

    /**
     * Проверяет, требуется ли проверка обновлений.
     *
     * @return Истина, если требуется проверка обновлений.
     */
    public boolean isCheckUpdates() {
        return checkUpdates;
    }

    /**
     * Устанавливает флаг необходимости проверки обновлений.
     *
     * @param checkUpdates Новое значение флага.
     */
    public void setCheckUpdates(boolean checkUpdates) {
        this.checkUpdates = checkUpdates;
    }

    /**
     * Проверяет, требуется ли перезагрузка.
     *
     * @return Истина, если требуется перезагрузка.
     */
    public boolean isRestartRequired() {
        return restartRequired;
    }

    /**
     * Устанавливает флаг необходимости перезагрузки.
     *
     * @param restartRequired Новое значение флага.
     */
    public void setRestartRequired(boolean restartRequired) {
        this.restartRequired = restartRequired;
    }
}