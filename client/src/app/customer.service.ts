import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Customer} from './customer';

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
    return this.http.get(`${this.baseUrl}${this.customerUrl}/${id}`);
  }

  createCustomer(customer: Customer): Observable<Object> {
    return this.http.post(`${this.baseUrl}${this.customerUrl}`, customer);
  }

  updateCustomer(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}${this.customerUrl}/${id}`, value);
  }

  deleteCustomer(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}${this.customerUrl}/${id}`, {responseType: 'text'});
  }

  getCustomerList(): Observable<any> {
    return this.http.get(`${this.baseUrl}${this.customerUrl}`);
  }
  searchCustomerList(firstName: string, lastName: string): Observable<any> {
    return this.http.get(`${this.baseUrl}${this.customerUrl}/search?firstName=${firstName}&lastName="${lastName}`);
  }

  getCustomerTypeList(): Observable<any> {
    return this.http.get(`${this.baseUrl}${this.customerTypeUrl}`);
  }

  getCustomerType(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}${this.customerTypeUrl}/${id}`);
  }
}
