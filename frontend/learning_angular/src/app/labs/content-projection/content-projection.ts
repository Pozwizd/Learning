import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CardShellComponent } from './card-shell';

@Component({
  selector: 'app-content-projection',
  imports: [RouterLink, CardShellComponent],
  templateUrl: './content-projection.html',
})
export class ContentProjectionComponent {
  protected readonly cards = [
    { id: 1, title: 'Собрать UI', body: 'Сделать переиспользуемые компоненты', action: 'Открыть доску' },
    { id: 2, title: 'Написать тесты', body: 'Покрыть критичные сценарии', action: 'Открыть тесты' },
  ];
}
