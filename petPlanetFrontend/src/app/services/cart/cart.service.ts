import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '@stripe/stripe-js';
import { OrderRequest } from 'src/app/Interfaces/OrderRequest';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private baseUrl = 'http://localhost:8080/order';

  constructor(private http: HttpClient) { }

  order(orderRequest: OrderRequest): Observable<Order> {
    return this.http.post<Order>(`${this.baseUrl}/add`, orderRequest);
  }

  getCartById(id: number): Observable<Order> {
    return this.http.get<Order>(`${this.baseUrl}/${id}`);
  }

  getAllCarts(page: number, size: number): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.baseUrl}/all?page=${page}&size=${size}`);
  }

  deleteCart(id: number): Observable<Order> {
    return this.http.delete<Order>(`${this.baseUrl}/${id}`);
  }

  changeOrderStatus(id: number, newStatus: string): Observable<Order> {
    return this.http.put<Order>(`${this.baseUrl}/change-status/${id}?newStatus=${newStatus}`, {});
  }
}
