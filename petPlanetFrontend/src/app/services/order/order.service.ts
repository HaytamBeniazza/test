import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from 'src/app/Interfaces/OrderItem';
import { OrderRequest } from 'src/app/Interfaces/OrderRequest';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseUrl = 'http://localhost:8080/order';

  constructor(private http: HttpClient) { }


  add(order: OrderRequest): Observable<Order> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Order>(`${this.baseUrl}/add`, order, { headers });
  }
}
