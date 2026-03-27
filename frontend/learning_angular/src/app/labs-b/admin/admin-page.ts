import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ADMIN_SECTION_NAME } from './admin-config';

@Component({
  selector: 'app-admin-page',
  imports: [RouterLink],
  templateUrl: './admin-page.html',
})
export class AdminPageComponent {
  protected readonly sectionName = inject(ADMIN_SECTION_NAME);
}
