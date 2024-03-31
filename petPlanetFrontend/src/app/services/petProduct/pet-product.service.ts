import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PetProduct } from 'src/app/Interfaces/PetProduct';

@Injectable({
  providedIn: 'root'
})
export class PetProductService {

  private baseUrl = 'http://localhost:8080/petproduct';

  constructor(private http: HttpClient) { }

  addProduct(petproduct: any): Observable<PetProduct> {
    console.log('====================================');
    console.log(petproduct);
    console.log('====================================');
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<PetProduct>(`${this.baseUrl}/add`, petproduct, { headers });
  }

  getAllProducts(page: number, size: number): Observable<PetProduct[]> {
    return this.http.get<PetProduct[]>(`${this.baseUrl}/list?page=${page}&size=${size}`);
  }

  getProductById(id: number): Observable<PetProduct> {
    return this.http.get<PetProduct>(`${this.baseUrl}/id/${id}`);
  }

  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

//   updateProduct(id: number, product: Product): Observable<Product> {
//     const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
//     return this.http.put<Product>(`${this.baseUrl}/${id}`, product, { headers });
//   }

  getTotalPagesNumber(pageSize : number) : Observable<number>{
    let pagesURL : string = `${this.baseUrl}/pages/${pageSize}`;
    return this.http.get<number>(pagesURL);
  }


}
