package org.example.Java._0_OOP._1_AdvancedModifiers.another_package;

import org.example.Java._0_OOP._1_AdvancedModifiers.BaseClass;

/**
 * Этот класс находится в ДРУГОМ ПАКЕТЕ.
 * Он демонстрирует, как меняется доступ при наследовании через границы пакетов.
 */
public class SubClassInAnotherPackage extends BaseClass {

    @Override
    public void mustBeImplemented() {
        System.out.println("[SubClassInAnotherPackage] Реализация абстрактного метода.");
    }

    @Override
    public void publicMethod() {
        System.out.println("[SubClassInAnotherPackage] Переопределенный публичный метод.");
    }

    /**
     * Мы МОЖЕМ переопределить protected метод, так как мы являемся подклассом.
     */
    @Override
    protected void protectedMethod() {
        System.out.println("[SubClassInAnotherPackage] Переопределенный protected метод (доступен, т.к. мы подкласс).");
    }

    // void packagePrivateMethod() { ... }
    // НЕВОЗМОЖНО: Метод с доступо�� package-private не виден за пределами своего пакета.
    // Мы не можем его ни вызвать, ни переопределить.

    public void testAccessFromAnotherPackage() {
        System.out.println("\n--- [SubClassInAnotherPackage] Тестируем доступ ИЗНУТРИ ---");
        System.out.print("Доступ к public: "); publicMethod();
        System.out.print("Доступ к protected: "); protectedMethod(); // Доступ есть, мы наследник
        // System.out.print("Доступ к package-private: "); packagePrivateMethod(); // ОШИБКА КОМПИЛЯЦИИ
        System.out.println("Доступ к package-private: НЕТ ДОСТУПА (другой пакет).");
        // System.out.print("Доступ к private: "); privateMethod(); // ОШИБКА КОМПИЛЯЦИИ
        System.out.println("Доступ к private: НЕТ ДОСТУПА.");
        System.out.println("--- Конец теста доступа из SubClassInAnotherPackage ---\n");
    }
}
