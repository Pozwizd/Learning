import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

type Filter = 'all' | 'todo' | 'in_progress' | 'done';
type Status = Exclude<Filter, 'all'>;

interface TaskItem {
  id: number;
  title: string;
  status: Status;
}

@Component({
  selector: 'app-control-flow',
  imports: [RouterLink],
  templateUrl: './control-flow.html',
})
export class ControlFlowComponent {
  protected filter: Filter = 'all';
  protected tasks: TaskItem[] = [
    { id: 1, title: 'Собрать layout', status: 'done' },
    { id: 2, title: 'Создать маршруты', status: 'in_progress' },
    { id: 3, title: 'Добавить тесты', status: 'todo' },
  ];

  protected setFilter(next: Filter): void {
    this.filter = next;
  }

  protected filteredTasks(): TaskItem[] {
    if (this.filter === 'all') {
      return this.tasks;
    }
    return this.tasks.filter((task) => task.status === this.filter);
  }
}
