import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-binding-basics',
  imports: [RouterLink],
  templateUrl: './binding-basics.html',
})
export class BindingBasicsComponent {
  // Поля компонента — это "состояние экрана", похоже на state в server-side MVC-модели,
  // только живет на клиенте и мгновенно обновляет шаблон.
  protected name = '';
  protected confirmedName = '';

  // Для Java-разработчика: Event здесь — браузерное событие,
  // target кастуем к HTMLInputElement, как явное приведение типа в Java.
  protected onNameInput(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.name = target.value;
  }

  protected confirm(): void {
    this.confirmedName = this.name.trim();
  }
}
