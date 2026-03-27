import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { OrderData } from './order-resolver';

@Component({
  selector: 'app-order-details',
  imports: [RouterLink],
  templateUrl: './order-details.html',
})
export class OrderDetailsComponent {
  protected order: OrderData | undefined;

  constructor(private readonly route: ActivatedRoute) {
    this.route.data.subscribe((data) => {
      this.order = data['order'] as OrderData | undefined;
    });
  }
}
