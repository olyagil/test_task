import {CustomerDetailsComponent} from '../customer-details/customer-details.component';
import {Observable} from 'rxjs';
import {CustomerService} from '../customer.service';
import {Customer} from '../customer';
import {Component, OnInit} from '@angular/core';
import {Router, Route} from '@angular/router';
import {CustomerType} from '../customer-type';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-component-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  customers: Observable<Customer[]>;
  firstName: string;
  lastName: string;

  constructor(private service: CustomerService, private router: Router) {
  }

  form = new FormGroup({
    first_name: new FormControl(),
    last_name: new FormControl()
  });

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.customers = this.service.getCustomerList();
  }

  customerDetails(id: number) {
    this.router.navigate(['details', id]);
  }

  addCustomer() {
    this.router.navigate(['add']);
  }


  searchForm(searchInfo) {
    this.firstName = this.FirstName.value;
    this.lastName = this.LastName.value;
    this.searchCustomer(this.firstName, this.lastName);
  }

  searchCustomer(firstName: string, lastName: string) {
    this.customers = this.service.searchCustomerList(firstName, lastName);

  }

  get FirstName() {
    return this.form.get('first_name');
  }

  get LastName() {
    return this.form.get('last_name');
  }

}
