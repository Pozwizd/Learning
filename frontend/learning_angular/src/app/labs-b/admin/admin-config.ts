import { InjectionToken, Provider } from '@angular/core';

export const ADMIN_SECTION_NAME = new InjectionToken<string>('ADMIN_SECTION_NAME');

export function provideAdminSection(name: string): Provider[] {
  return [{ provide: ADMIN_SECTION_NAME, useValue: name }];
}
