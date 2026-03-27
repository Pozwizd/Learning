import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';

interface Product {
  id: number;
  name: string;
  category: string;
}

@Component({
  selector: 'app-product-details',
  imports: [RouterLink],
  templateUrl: './product-details.html',
})
export class ProductDetailsComponent {
  protected product: Product | undefined;

  private readonly products: Product[] = [
    { id: 1, name: 'Ноутбук', category: 'Техника' },
    { id: 2, name: 'Наушники', category: 'Аксессуары' },
    { id: 3, name: 'Клавиатура', category: 'Периферия' },
  ];

  constructor(private readonly route: ActivatedRoute) {
    this.route.paramMap.subscribe((params) => {
      const id = Number(params.get('id'));
      this.product = this.products.find((item) => item.id === id);
    });
  }
}
