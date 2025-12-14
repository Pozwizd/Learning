package org.example.Java._0_OOP._1_AdvancedModifiers;

/**
 * Этот абстрактный класс является основой для демонстрации модификаторов доступа.
 */
public abstract class BaseClass {

    // --- 1. Абстрактный метод (должен быть реализован подклассом) ---
    /**
     * public abstract: Стандартный абстрактный метод.
     * Должен быть реализован любым не-абстрактным подклассом.
     */
    public abstract void mustBeImplemented();


    // --- 2. Методы с разными модификаторами доступа ---

    /**
     * public: Доступен отовсюду (в этом пакете, в других пакетах, в подклассах).
     */
    public void publicMethod() {
        System.out.println("[BaseClass] Это публичный метод.");
    }

    /**
     * protected: Доступен внутри этого пакета и для всех подклассов (даже в других пакетах).
     */
    protected void protectedMethod() {
        System.out.println("[BaseClass] Это защищенный (protected) метод.");
    }

    /**
     * package-private (default): Доступен только для классов внутри этого же пакета.
     * Подклассы в других пакетах не имеют к нему доступа.
     */
    void packagePrivateMethod() {
        System.out.println("[BaseClass] Это метод с доступом на уровне пакета (package-private).");
    }

    /**
     * private: Доступен ТОЛЬКО внутри этого класса (BaseClass).
     * Никто другой, даже подклассы, не могут его видеть или вызывать.
     */
    private void privateMethod() {
        System.out.println("[BaseClass] Это приватный метод. Вы не должны видеть это сообщение извне.");
    }

    // Демонстрация вызова приватного метода из публичного
    public void callPrivateMethod() {
        System.out.println("[BaseClass] Вызываю приватный метод из публичного...");
        privateMethod();
    }


    // --- 3. Методы с другими модификаторами ---

    /**
     * final: Этот метод нельзя переопределить (override) в подклассах.
     */
    public final void finalMethod() {
        System.out.println("[BaseClass] Это финальный (final) метод. Его нельзя переопределить.");
    }

    /**
     * static: Этот метод принадлежит классу, а не экземпляру объекта.
     * Его можно "скрыть" (hide) в подклассе, но не переопределить (override) в полиморфном смысле.
     */
    public static void staticMethod() {
        System.out.println("[BaseClass] Это статический (static) метод.");
    }


    // --- 4. Недопустимые комбинации модификаторов (для объяснения) ---

    // private abstract void invalidAbstractPrivate();
    // ОШИБКА КОМПИЛЯЦИИ: `private` и `abstract` несовместимы.
    // ПОЧЕМУ: `abstract` означает, что метод ДОЛЖЕН быть реализован в подклассе.
    // `private` означает, что метод НЕ ВИДЕН подклассу. Подкласс не может реализовать то, чего не видит.

    // final abstract void invalidAbstractFinal();
    // ОШИБКА КОМПИЛЯЦИИ: `final` и `abstract` несовместимы.
    // ПОЧЕМУ: `abstract` требует, чтобы метод был переопределен, а `final` это запрещает.

    // static abstract void invalidAbstractStatic();
    // ОШИБКА КОМПИЛЯЦИИ: `static` и `abstract` несовместимы.
    // ПОЧЕМУ: `abstract` методы связаны с полиморфизмом и экземплярами объектов.
    // `static` методы принадлежат классу и не могут быть переопределены полиморфно.
}
