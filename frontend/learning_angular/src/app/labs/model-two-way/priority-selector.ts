import { Component, model } from '@angular/core';

@Component({
  selector: 'app-priority-selector',
  template: `
    <p>Приоритет в дочернем компоненте: <strong>{{ priority() }}</strong></p>
    <button type="button" (click)="setPriority(1)">Низкий</button>
    <button type="button" (click)="setPriority(2)">Средний</button>
    <button type="button" (click)="setPriority(3)">Высокий</button>
  `,
})
export class PrioritySelectorComponent {
  // model() — современный способ сделать двустороннюю связь для custom-компонента.
  // Аналогия с Java: как mutable поле + синхронизация через setter/getter, но декларативно.
  readonly priority = model<number>(2);

  protected setPriority(level: number): void {
    this.priority.set(level);
  }
}
