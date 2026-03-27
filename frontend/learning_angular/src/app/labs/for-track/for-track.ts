import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

interface CardItem {
  id: number;
  label: string;
}

@Component({
  selector: 'app-for-track',
  imports: [RouterLink],
  templateUrl: './for-track.html',
})
export class ForTrackComponent {
  protected newLabel = '';
  protected nextId = 4;
  protected cards: CardItem[] = [
    { id: 1, label: 'Альфа' },
    { id: 2, label: 'Бета' },
    { id: 3, label: 'Гамма' },
  ];

  protected onInput(event: Event): void {
    const target = event.target as HTMLInputElement;
    this.newLabel = target.value;
  }

  protected addCard(): void {
    const label = this.newLabel.trim();
    if (!label) {
      return;
    }
    this.cards = [...this.cards, { id: this.nextId++, label }];
    this.newLabel = '';
  }

  protected removeCard(id: number): void {
    this.cards = this.cards.filter((card) => card.id !== id);
  }
}
