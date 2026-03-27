import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-events',
  imports: [RouterLink],
  templateUrl: './events.html',
})
export class EventsComponent {
  protected counter = 0;

  protected increment(): void {
    this.counter += 1;
  }

  protected decrement(): void {
    this.counter -= 1;
  }

  protected reset(): void {
    this.counter = 0;
  }
}
