import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comment } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  private baseUrl = 'http://localhost:8080/comment'; // Replace with your backend URL

  constructor(private http: HttpClient) { }

  addComment(comment: Comment): Observable<Comment> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Comment>(`${this.baseUrl}/add`, comment, { headers });
  }

  getAllComments(page: number, size: number): Observable<Comment[]> {
    return this.http.get<Comment[]>(`${this.baseUrl}/list?page=${page}&size=${size}`);
  }

  getCommentById(id: number): Observable<Comment> {
    return this.http.get<Comment>(`${this.baseUrl}/id/${id}`);
  }

  deleteComment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  updateComment(id: number, comment: Comment): Observable<Comment> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Comment>(`${this.baseUrl}/${id}`, comment, { headers });
  }
}
