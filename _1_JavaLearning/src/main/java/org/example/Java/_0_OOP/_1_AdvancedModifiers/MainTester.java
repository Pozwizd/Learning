package org.example.Java._0_OOP._1_AdvancedModifiers;

import org.example.Java._0_OOP._1_AdvancedModifiers.another_package.SubClassInAnotherPackage;

public class MainTester {
    public static void main(String[] args) {
        System.out.println("========================================================");
        System.out.println("СЦЕНАРИЙ 1: РАБОТА С ПОДКЛАССОМ В ТОМ ЖЕ ПАКЕТЕ");
        System.out.println("========================================================");

        SubClass subClass = new SubClass();
        subClass.mustBeImplemented();
        subClass.publicMethod();
        subClass.protectedMethod(); // Доступно, т.к. мы в том же пакете
        subClass.packagePrivateMethod(); // Доступно, т.к. мы в том же пакете
        // subClass.privateMethod(); // ОШИБКА КОМПИЛЯЦИИ - private не виден
        subClass.callPrivateMethod(); // Работает, т.к. вызывается публичный метод родителя
        subClass.finalMethod();

        // Демонстрация доступа к методам изнутри самого класса
        subClass.testAccess();


        System.out.println("========================================================");
        System.out.println("СЦЕНАРИЙ 2: РАБОТА С ПОДКЛАССОМ В ДРУГОМ ПАКЕТЕ");
        System.out.println("========================================================");

        SubClassInAnotherPackage subClassOtherPackage = new SubClassInAnotherPackage();
        subClassOtherPackage.mustBeImplemented();
        subClassOtherPackage.publicMethod();
        // subClassOtherPackage.protectedMethod();
        // ОШИБКА КОМПИЛЯЦИИ! ВАЖНЫЙ МОМЕНТ:
        // Хотя SubClassInAnotherPackage *может* переопределить protectedMethod,
        // мы из MainTester (который не является его подклассом) не можем вызвать
        // этот метод через ссылку, т.к. мы не в том же пакете.
        // Сам объект SubClassInAnotherPackage может вызывать свой protectedMethod изнутри.
        System.out.println("[MainTester] Попытка вызвать protected метод из другого пакета... НЕЛЬЗЯ.");

        // subClassOtherPackage.packagePrivateMethod(); // ОШИБКА КОМПИЛЯЦИИ - не виден в другом пакете
        System.out.println("[MainTester] Попытка вызвать package-private метод из другого пакета... НЕЛЬЗЯ.");

        // Демонстрация доступа изнутри класса в другом пакете
        subClassOtherPackage.testAccessFromAnotherPackage();


        System.out.println("========================================================");
        System.out.println("СЦЕНАРИЙ 3: ПОЛИМОРФИЗМ И СТАТИЧЕСКИЕ МЕТОДЫ");
        System.out.println("========================================================");

        BaseClass polySubClass = new SubClass();
        System.out.print("Вызов статического метода через ссылку на родителя: ");
        polySubClass.staticMethod(); // Вызывается метод BaseClass, т.к. ссылка имеет тип BaseClass

        System.out.print("Вызов статического метода через ссылку на потомка: ");
        SubClass realSubClass = new SubClass();
        realSubClass.staticMethod(); // Вызывается метод SubClass, т.к. ссылка имеет тип SubClass

        System.out.println("\nВывод: статические методы не проявляют полиморфизм. Выбор метода зависит от типа ССЫЛКИ, а не ОБЪЕКТА.");
    }
}
