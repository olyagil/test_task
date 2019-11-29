import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Customer} from '../model/customer';
import {CustomerType} from '../model/customer-type';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8080/';
  private customerUrl = 'customers';
  private customerTypeUrl = 'customer-types';

  constructor(private http: HttpClient) {
  }

  getCustomer(id: number): Observable<any> {
    return this.http.get<Customer>(`${this.baseUrl}${this.customerUrl}/${id}`);
  }

  createCustomer(customer: Customer) {
    return this.http.post<Customer>(`${this.baseUrl}${this.customerUrl}`, customer);
  }

  updateCustomer(id: number, value: any) {
    return this.http.put<Customer>(`${this.baseUrl}${this.customerUrl}/${id}`, value);
  }

  deleteCustomer(id: number): Observable<any> {
    return this.http.delete<Customer>(`${this.baseUrl}${this.customerUrl}/${id}`);
  }

  getCustomerList(): Observable<any> {
    return this.http.get<Customer[]>(`${this.baseUrl}${this.customerUrl}`);
  }

  searchCustomerList(firstName: string, lastName: string): Observable<any> {
    return this.http.get<Customer[]>(`${this.baseUrl}${this.customerUrl}/search?firstName=${firstName}&lastName=${lastName}`);
  }

  getCustomerTypeList(): Observable<any> {
    return this.http.get<CustomerType[]>(`${this.baseUrl}${this.customerTypeUrl}`);
  }

}
