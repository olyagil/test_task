import {Component, OnInit} from '@angular/core';
import {Customer} from '../customer';
import {CustomerService} from '../customer.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import {CustomerType} from '../customer-type';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {
  id: number;
  customer: Customer;
  customerTypes: Observable<CustomerType[]>;
  submitted = false;

  constructor(private route: ActivatedRoute, private router: Router,
              private service: CustomerService) {
  }

  updateForm = new FormGroup({
    title: new FormControl('', [Validators.required, Validators.maxLength(3), Validators.pattern('[a-zA-Z ]*')]),
    first_name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50),
      Validators.pattern('[a-zA-Z ]*')]),
    last_name: new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(50),
      Validators.pattern('[a-zA-Z ]*')]),
    type: new FormControl('', [Validators.required])
  });

  ngOnInit() {
    this.submitted = false;
    this.customer = new Customer();
    this.id = this.route.snapshot.params.id;
    this.reloadCustomerTypes();

    this.service.getCustomer(this.id)
      .subscribe(data => {
        console.log(data);
        this.customer = data;
      }, error => console.log(error));
  }

  onSubmit() {
    console.log(this.updateForm);
    this.customer.title = this.Title.value;
    this.customer.firstName = this.FirstName.value;
    this.customer.lastName = this.LastName.value;
    this.customer.typeId = this.Type.value;
    this.update();
    this.submitted = true;
  }

  update() {
    this.service.updateCustomer(this.id, this.customer)
      .subscribe(data => console.log(data), error => console.log(error));
  }

  reloadCustomerTypes() {
    this.customerTypes = this.service.getCustomerTypeList();
  }

  get Title() {
    return this.updateForm.get('title');
  }

  get FirstName() {
    return this.updateForm.get('first_name');
  }

  get LastName() {
    return this.updateForm.get('last_name');
  }

  get Type() {
    return this.updateForm.get('type');
  }
}
