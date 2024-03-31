import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Review } from 'src/app/Interfaces/Review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  private baseUrl = 'http://localhost:8080/review';

  constructor(private http: HttpClient) { }

  addReview(review: Review): Observable<Review> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Review>(`${this.baseUrl}/add`, review, { headers });
  }

  getAllReviews(page: number, size: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.baseUrl}/list?page=${page}&size=${size}`);
  }

  getReviewById(id: number): Observable<Review> {
    return this.http.get<Review>(`${this.baseUrl}/${id}`);
  }

  deleteReview(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  updateReview(id: number, review: Review): Observable<Review> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Review>(`${this.baseUrl}/${id}`, review, { headers });
  }
}
