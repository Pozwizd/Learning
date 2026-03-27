import { ResolveFn } from '@angular/router';

export interface OrderData {
  id: number;
  customer: string;
  total: number;
}

const ORDERS: OrderData[] = [
  { id: 100, customer: 'Иван', total: 2500 },
  { id: 200, customer: 'Мария', total: 4100 },
  { id: 300, customer: 'Олег', total: 980 },
];

export const orderResolver: ResolveFn<OrderData | undefined> = (route) => {
  const id = Number(route.paramMap.get('id'));
  return ORDERS.find((order) => order.id === id);
};
