import { Component } from '@angular/core';

/*
  Для Java-разработчика:
  @Component — это метаданные класса (аналог аннотации), где мы описываем,
  как Angular должен "собрать" UI-компонент.
  selector — имя тега компонента, как "точка входа" в шаблоне родителя.
  template — встроенный HTML-шаблон компонента.

  <ng-content> — механизм проекции контента.
  Аналогия: как если бы Java-компонент принимал "слоты" (header/body/footer)
  и рендерил их в своих зонах.
*/
@Component({
  selector: 'app-card-shell',
  template: `
    <article style="border: 1px solid #ccc; border-radius: 8px; padding: 12px; margin-bottom: 12px;">
      <header style="font-weight: 700; margin-bottom: 8px;">
        <!-- Берем только контент, помеченный атрибутом card-title -->
        <ng-content select="[card-title]"></ng-content>
      </header>
      <section>
        <!-- Основной (неименованный) слот -->
        <ng-content></ng-content>
      </section>
      <footer style="margin-top: 8px;">
        <!-- Берем только контент, помеченный атрибутом card-footer -->
        <ng-content select="[card-footer]"></ng-content>
      </footer>
    </article>
  `,
})
export class CardShellComponent {}
