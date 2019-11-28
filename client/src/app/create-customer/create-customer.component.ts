import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CustomerService} from '../customer.service';
import {Customer} from '../customer';
import {Observable} from 'rxjs';
import {CustomerType} from '../customer-type';

@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {
  customer: Customer = new Customer();
  customerTypes: Observable<CustomerType[]>;
  submitted = false;

  constructor(private service: CustomerService, private router: Router) {
  }

  createForm = new FormGroup({
    title: new FormControl('', [Validators.required, Validators.maxLength(3), Validators.pattern('[a-zA-Z ]*')]),
    first_name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50),
      Validators.pattern('[a-zA-Z ]*')]),
    last_name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50),
      Validators.pattern('[a-zA-Z ]*')]),
    type: new FormControl('', [Validators.required])
  });

  ngOnInit() {
    this.submitted = false;
    this.reloadCustomerTypes();
  }

  onSubmit() {
    console.log(this.createForm.value);
    this.customer = new Customer();
    this.customer.title = this.Title.value;
    this.customer.firstName = this.FirstName.value;
    this.customer.lastName = this.LastName.value;
    this.customer.typeId = this.Type.value;
    this.save();
    this.submitted = true;
  }

  save() {
    this.service.createCustomer(this.customer)
      .subscribe(data => console.log(data), error => console.error(error));
  }

  reloadCustomerTypes() {
    this.customerTypes = this.service.getCustomerTypeList();
  }

  get Title() {
    return this.createForm.get('title');
  }

  get FirstName() {
    return this.createForm.get('first_name');
  }

  get LastName() {
    return this.createForm.get('last_name');
  }

  get Type() {
    return this.createForm.get('type');
  }
}
