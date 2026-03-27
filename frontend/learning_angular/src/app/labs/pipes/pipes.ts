import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { StatusLabelPipe } from './status-label.pipe';

interface OrderItem {
  id: number;
  amount: number;
  createdAt: Date;
  status: 'new' | 'processing' | 'completed';
}

@Component({
  selector: 'app-pipes',
  imports: [CommonModule, RouterLink, StatusLabelPipe],
  templateUrl: './pipes.html',
})
export class PipesComponent {
  protected readonly orders: OrderItem[] = [
    { id: 1001, amount: 129.99, createdAt: new Date('2026-01-10'), status: 'new' },
    { id: 1002, amount: 89.5, createdAt: new Date('2026-01-12'), status: 'processing' },
    { id: 1003, amount: 430, createdAt: new Date('2026-01-18'), status: 'completed' },
  ];
}
