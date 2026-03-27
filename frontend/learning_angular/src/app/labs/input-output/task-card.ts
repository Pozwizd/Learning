import { Component, input, output } from '@angular/core';

// export interface — контракт данных карточки (аналог Java DTO record/class).
export interface TaskCardItem {
  id: number;
  title: string;
  status: 'todo' | 'in_progress' | 'done';
}

@Component({
  selector: 'app-task-card',
  templateUrl: './task-card.html',
})
export class TaskCardComponent {
  // input.required<T>() — обязательный входной параметр компонента.
  // Для Java-разработчика: похоже на required dependency в конструкторе.
  readonly task = input.required<TaskCardItem>();
  // output<void>() — событие наружу без payload (аналог callback/listener сигнала "удалить").
  readonly remove = output<void>();
}
