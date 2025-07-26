package org.example.Java._0_OOP._1_AdvancedModifiers;

/**
 * Этот класс находится в ТОМ ЖЕ ПАКЕТЕ, что и BaseClass.
 * Он демонстрирует, к чему имеет доступ подкласс "изнутри".
 */
public class SubClass extends BaseClass {

    // 1. РЕАЛИЗАЦИЯ АБСТРАКТНОГО МЕТОДА (ОБЯЗАТЕЛЬНО)
    @Override
    public void mustBeImplemented() {
        System.out.println("[SubClass] Реализация обязательного абстрактного метода.");
    }

    // 2. ПЕРЕОПРЕДЕЛЕНИЕ МЕТОДОВ
    @Override
    public void publicMethod() {
        System.out.println("[SubClass] Переопределенный публичный метод.");
        // Мы все еще можем вызвать версию из родительского класса
        System.out.print("  -> Вызов super.publicMethod(): ");
        super.publicMethod();
    }

    @Override
    protected void protectedMethod() {
        System.out.println("[SubClass] Переопределенный protected метод.");
        System.out.print("  -> Вызов super.protectedMethod(): ");
        super.protectedMethod();
    }

    @Override
    void packagePrivateMethod() {
        System.out.println("[SubClass] Переопределенный package-private метод (возможно, т.к. мы в том же пакете).");
        System.out.print("  -> Вызов super.packagePrivateMethod(): ");
        super.packagePrivateMethod();
    }

    // private void privateMethod() { ... }
    // НЕВОЗМОЖНО: Мы не можем переопределить приватный метод, потому что мы его не видим.
    // Если мы создадим здесь `private void privateMethod()`, это будет совершенно новый,
    // никак не связанный с родительским, метод.

    // public final void finalMethod() { ... }
    // ОШИБКА КОМПИЛЯЦИИ: Нельзя переопределить final метод.

    /**
     * Это "сокрытие" (hiding) статического метода, а не переопределение.
     * Выбор метода будет зависеть от типа ссылки, а не от типа объекта.
     */
    public static void staticMethod() {
        System.out.println("[SubClass] Это статический метод, который 'скрывает' родительский.");
    }

    public void testAccess() {
        System.out.println("\n--- [SubClass] Тестируем доступ ИЗНУТРИ SubClass ---");
        System.out.print("Доступ к public: "); publicMethod();
        System.out.print("Доступ к protected: "); protectedMethod();
        System.out.print("Доступ к package-private: "); packagePrivateMethod();
        // System.out.print("Доступ к private: "); privateMethod(); // ОШИБКА КОМПИЛЯЦИИ
        System.out.println("Доступ к private из BaseClass: НЕТ ПРЯМОГО ДОСТУПА. Только через публичный метод родителя.");
        callPrivateMethod();
        System.out.println("--- Конец теста доступа из SubClass ---\n");
    }
}
