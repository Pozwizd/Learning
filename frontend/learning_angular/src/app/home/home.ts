import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
})
export class HomeComponent {
  protected readonly blockA = [
    { path: '/lab/component-basics', title: '1. Базовый компонент' },
    { path: '/lab/binding-basics', title: '2. Биндинги' },
    { path: '/lab/events', title: '3. События' },
    { path: '/lab/control-flow', title: '4. Управляющие блоки шаблона' },
    { path: '/lab/for-track', title: '5. @for + track' },
    { path: '/lab/input-output', title: '6. input / output' },
    { path: '/lab/model-two-way', title: '7. model() двусторонняя связь' },
    { path: '/lab/content-projection', title: '8. Проекция контента' },
    { path: '/lab/pipes', title: '9. Пайпы' },
  ];

  protected readonly blockB = [
    { path: '/lab-b/router-basics', title: '10. Базовый роутинг' },
    { path: '/lab-b/product/1', title: '11. Параметры маршрута' },
    { path: '/lab-b/search', title: '12. Query params' },
    { path: '/lab-b/legacy', title: '13. Redirect + NotFound' },
    { path: '/lab-b/account', title: '14. Nested routes (Account)' },
    { path: '/lab-b/admin', title: '15. Route-level providers' },
    { path: '/lab-b/login', title: '16. Guard: Login/Private' },
    { path: '/lab-b/order/100', title: '17. Resolver: Order details' },
  ];

  protected readonly extra = [
    { path: '/second', title: 'Старая экспериментальная страница' },
  ];
}
