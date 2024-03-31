import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from 'src/app/Interfaces/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/product';

  constructor(private http: HttpClient) { }

  addProduct(product: any): Observable<Product> {
    const productForm = new FormData();
          productForm.append('name', product.name);
          productForm.append('description', product.description);
          productForm.append('quantity', product.quantity);
          productForm.append('price', product.price);
          productForm.append('image', product.image as File);
          
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'multipart/form-data');

    return this.http.post<Product>(`${this.baseUrl}`, productForm, { headers });
  }

  getAllProducts(page: number, size: number): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/list?page=${page}&size=${size}`);
  }

  getProductById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.baseUrl}/id/${id}`);
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  updateProduct(id: number, product: Product): Observable<Product> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Product>(`${this.baseUrl}/${id}`, product, { headers });
  }
}
