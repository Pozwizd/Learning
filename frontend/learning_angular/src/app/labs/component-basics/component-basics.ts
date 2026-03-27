import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

// Для Java-разработчика:
// export class = публичный класс для импорта в других файлах.
@Component({
  selector: 'app-component-basics',
  imports: [RouterLink],
  templateUrl: './component-basics.html',
})
export class ComponentBasicsComponent {
  protected title = 'Базовый компонент';
  protected message = 'Этот текст приходит из полей TypeScript-класса.';
  protected clicks = 0;

  // Метод компонента вызывается из шаблона по событию click.
  // Это похоже на вызов метода контроллера, только на уровне UI-состояния.
  protected refreshMessage(): void {
    this.clicks += 1;
    this.message = `Сообщение обновлено: ${this.clicks} раз(а).`;
  }
}
