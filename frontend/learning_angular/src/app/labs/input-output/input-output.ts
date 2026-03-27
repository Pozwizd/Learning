import { Component, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { TaskCardComponent, TaskCardItem } from './task-card';

@Component({
  selector: 'app-input-output',
  imports: [RouterLink, TaskCardComponent],
  templateUrl: './input-output.html',
})
export class InputOutputComponent {
  // protected readonly + signal:
  // 1) protected — доступно шаблону, но не "наружу" как API класса;
  // 2) readonly — ссылка на signal не переназначается;
  // 3) signal<T> — реактивное состояние экрана.
  protected readonly tasks = signal<TaskCardItem[]>([
    { id: 1, title: 'Прочитать требования', status: 'done' },
    { id: 2, title: 'Сделать фичу', status: 'in_progress' },
    { id: 3, title: 'Написать тесты', status: 'todo' },
  ]);

  protected removeTask(id: number): void {
    this.tasks.update((prev) => prev.filter((task) => task.id !== id));
  }
}
