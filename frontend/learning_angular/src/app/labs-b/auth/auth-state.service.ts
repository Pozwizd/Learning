import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthStateService {
  protected readonly isLoggedInSignal = signal(false);

  isLoggedIn(): boolean {
    return this.isLoggedInSignal();
  }

  login(): void {
    this.isLoggedInSignal.set(true);
  }

  logout(): void {
    this.isLoggedInSignal.set(false);
  }
}
