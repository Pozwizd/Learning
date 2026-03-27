import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthStateService } from './auth-state.service';

@Component({
  selector: 'app-lab-b-login',
  imports: [RouterLink],
  templateUrl: './login.html',
})
export class LabBLoginComponent {
  constructor(private readonly authState: AuthStateService) {}

  protected login(): void {
    this.authState.login();
  }

  protected logout(): void {
    this.authState.logout();
  }

  protected isLoggedIn(): boolean {
    return this.authState.isLoggedIn();
  }
}
