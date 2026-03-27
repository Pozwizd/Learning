import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statusLabel',
  standalone: true,
})
// PipeTransform — контракт преобразования значения для отображения (как mapper/formatter).
export class StatusLabelPipe implements PipeTransform {
  transform(value: 'new' | 'processing' | 'completed'): string {
    if (value === 'new') return 'Новая';
    if (value === 'processing') return 'В обработке';
    return 'Завершена';
  }
}
