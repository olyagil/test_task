import {Component, OnInit, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Customer} from '../../model/customer';
import {CustomerService} from '../../service/customer.service';
import {CustomerListComponent} from '../customer-list/customer-list.component';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  id: number;
  customer: Customer;
  submitted = false;

  constructor(private route: ActivatedRoute, private router: Router,
              private service: CustomerService) {
  }

  ngOnInit() {
    this.customer = new Customer();
    this.id = this.route.snapshot.params.id;
    this.service.getCustomer(this.id)
      .subscribe(data => {
        console.log(data);
        this.customer = data;
      }, error => console.log(error));
  }


  updateCustomer(id: number) {
    this.router.navigate(['update', id]);
  }

  deleteCustomer(id: number) {
    if (confirm('Are you sure to delete customer with id: ' + id)) {
      this.service.deleteCustomer(id)
        .subscribe(
          data => {
            console.log(data);
          },
          error => console.log(error)
        );
      this.submitted = true;
    }
  }

  goToList() {
    this.router.navigate(['customers']);
  }

}
