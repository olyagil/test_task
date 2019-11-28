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
  form: FormGroup;
  submitted = false;

  constructor(private route: ActivatedRoute, private router: Router,
              private service: CustomerService, private builder: FormBuilder) {
    this.createForm();
  }

  createForm() {
    this.form = this.builder.group({
      title: ['', Validators.required, Validators.maxLength(3)],
      first_name: ['', Validators.required, Validators.minLength(3), Validators.maxLength(50)],
      last_name: ['', Validators.required, Validators.minLength(3), Validators.maxLength(50)],
      type : ['']
    });
  }

  ngOnInit() {
    this.submitted = false;
    this.customer = new Customer();
    this.id = this.route.snapshot.params.id;
    if (!this.id) {
      alert('Invalid action.');
      this.goToList();
      return;
    }
    this.reloadCustomerTypes();

    this.service.getCustomer(this.id)
      .subscribe(data => {
        console.log(data);
        this.customer = data;
      }, error => console.log(error));
  }

  reloadCustomerTypes() {
    this.customerTypes = this.service.getCustomerTypeList();
  }

  onSubmit() {
    console.log(this.createForm);
    this.customer = new Customer();
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
    // this.customer = new Customer();
    this.goToList();
  }

  goToList() {
    this.router.navigate(['customers']);
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
