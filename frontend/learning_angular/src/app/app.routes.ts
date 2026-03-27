import { Routes } from '@angular/router';
import { HomeComponent } from './home/home';
import { SecondComponent } from './second/second';
import { BindingBasicsComponent } from './labs/binding-basics/binding-basics';
import { ComponentBasicsComponent } from './labs/component-basics/component-basics';
import { ContentProjectionComponent } from './labs/content-projection/content-projection';
import { ControlFlowComponent } from './labs/control-flow/control-flow';
import { EventsComponent } from './labs/events/events';
import { ForTrackComponent } from './labs/for-track/for-track';
import { InputOutputComponent } from './labs/input-output/input-output';
import { ModelTwoWayComponent } from './labs/model-two-way/model-two-way';
import { PipesComponent } from './labs/pipes/pipes';
import { AccountBillingComponent } from './labs-b/account/billing';
import { AccountProfileComponent } from './labs-b/account/profile';
import { AccountSecurityComponent } from './labs-b/account/security';
import { AccountShellComponent } from './labs-b/account/account-shell';
import { provideAdminSection } from './labs-b/admin/admin-config';
import { AdminPageComponent } from './labs-b/admin/admin-page';
import { authGuard } from './labs-b/auth/auth.guard';
import { LabBLoginComponent } from './labs-b/auth/login';
import { LabBPrivateComponent } from './labs-b/auth/private';
import { LabBNotFoundComponent } from './labs-b/not-found/lab-b-not-found';
import { OrderDetailsComponent } from './labs-b/order/order-details';
import { orderResolver } from './labs-b/order/order-resolver';
import { ProductDetailsComponent } from './labs-b/product-details/product-details';
import { RouterBasicsAboutComponent } from './labs-b/router-basics-about/router-basics-about';
import { RouterBasicsHelpComponent } from './labs-b/router-basics-help/router-basics-help';
import { RouterBasicsComponent } from './labs-b/router-basics/router-basics';
import { SearchQueryComponent } from './labs-b/search-query/search-query';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'second', component: SecondComponent },

  { path: 'lab/component-basics', component: ComponentBasicsComponent },
  { path: 'lab/binding-basics', component: BindingBasicsComponent },
  { path: 'lab/events', component: EventsComponent },
  { path: 'lab/control-flow', component: ControlFlowComponent },
  { path: 'lab/for-track', component: ForTrackComponent },
  { path: 'lab/input-output', component: InputOutputComponent },
  { path: 'lab/model-two-way', component: ModelTwoWayComponent },
  { path: 'lab/content-projection', component: ContentProjectionComponent },
  { path: 'lab/pipes', component: PipesComponent },

  {
    path: 'lab-b',
    children: [
      { path: '', redirectTo: 'router-basics', pathMatch: 'full' },
      { path: 'router-basics', component: RouterBasicsComponent },
      { path: 'router-basics/about', component: RouterBasicsAboutComponent },
      { path: 'router-basics/help', component: RouterBasicsHelpComponent },
      { path: 'product/:id', component: ProductDetailsComponent },
      { path: 'search', component: SearchQueryComponent },
      { path: 'legacy', redirectTo: 'router-basics', pathMatch: 'full' },
      { path: 'not-found', component: LabBNotFoundComponent },
      {
        path: 'account',
        component: AccountShellComponent,
        children: [
          { path: '', redirectTo: 'profile', pathMatch: 'full' },
          { path: 'profile', component: AccountProfileComponent },
          { path: 'security', component: AccountSecurityComponent },
          { path: 'billing', component: AccountBillingComponent },
        ],
      },
      {
        path: 'admin',
        component: AdminPageComponent,
        providers: [...provideAdminSection('Администрирование: пользователи и роли')],
      },
      { path: 'login', component: LabBLoginComponent },
      { path: 'private', component: LabBPrivateComponent, canActivate: [authGuard] },
      { path: 'order/:id', component: OrderDetailsComponent, resolve: { order: orderResolver } },
      { path: '**', component: LabBNotFoundComponent },
    ],
  },

  { path: '**', redirectTo: '' },
];
