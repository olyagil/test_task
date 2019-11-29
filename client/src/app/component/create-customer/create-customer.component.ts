import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {CustomerService} from '../../service/customer.service';
import {Customer} from '../../model/customer';
import {Observable} from 'rxjs';
import {CustomerType} from '../../model/customer-type';

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
    this.customer = new Customer();
  }

  createForm = new FormGroup({
    title: new FormControl('', [
      Validators.required,
      Validators.maxLength(3),
      Validators.pattern('[a-zA-Z ]*')]),
    first_name: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50),
      Validators.pattern('[a-zA-Z ]*')]),
    last_name: new FormControl('', [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50),
      Validators.pattern('[a-zA-Z ]*')]),
    type: new FormControl('', [Validators.required])
  });

  ngOnInit() {
    this.submitted = false;
    this.loadCustomerTypes();
  }

  onSubmit() {
    console.log(this.createForm.value);
    this.customer.title = this.title.value;
    this.customer.firstName = this.firstName.value;
    this.customer.lastName = this.lastName.value;
    this.customer.typeId = this.type.value;
    this.save();
    this.submitted = true;
  }

  save() {
    this.service.createCustomer(this.customer)
      .subscribe(data => console.log(data), error => console.error(error));
  }

  loadCustomerTypes() {
    this.customerTypes = this.service.getCustomerTypeList();
  }

  get title() {
    return this.createForm.get('title');
  }

  get firstName() {
    return this.createForm.get('first_name');
  }

  get lastName() {
    return this.createForm.get('last_name');
  }

  get type() {
    return this.createForm.get('type');
  }
}
