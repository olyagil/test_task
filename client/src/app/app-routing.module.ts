import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CustomerDetailsComponent} from './customer-details/customer-details.component';
import {CreateCustomerComponent} from './create-customer/create-customer.component';
import {CustomerListComponent} from './customer-list/customer-list.component';
import {UpdateCustomerComponent} from './update-customer/update-customer.component';
import {HomeComponent} from './home/home.component';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'customers', component: CustomerListComponent},
  {path: 'add', component: CreateCustomerComponent },
  {path: 'update/:id', component: UpdateCustomerComponent },
  {path: 'details/:id', component: CustomerDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
