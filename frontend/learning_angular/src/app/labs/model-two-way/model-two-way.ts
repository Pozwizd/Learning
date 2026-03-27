import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PrioritySelectorComponent } from './priority-selector';

@Component({
  selector: 'app-model-two-way',
  imports: [RouterLink, PrioritySelectorComponent],
  templateUrl: './model-two-way.html',
})
export class ModelTwoWayComponent {
  protected priority = 2;

  protected priorityLabel(): string {
    if (this.priority === 1) return 'Низкий';
    if (this.priority === 2) return 'Средний';
    return 'Высокий';
  }
}
