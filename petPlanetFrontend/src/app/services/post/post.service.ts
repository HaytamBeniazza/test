import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from 'src/app/Interfaces/Post';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private baseUrl = 'http://localhost:8080/post'; // Replace with your backend URL

  constructor(private http: HttpClient) {}

  addPost(post: Post): Observable<Post> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Post>(`${this.baseUrl}/add`, post, { headers });
  }

  getAllPosts(page: number, size: number): Observable<Post[]> {
    return this.http.get<Post[]>(
      `${this.baseUrl}/list?page=${page}&size=${size}`
    );
  }

  getPostById(id: number): Observable<Post> {
    return this.http.get<Post>(`${this.baseUrl}/id/${id}`);
  }

  deletePost(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  updatePost(id: number, post: Post): Observable<Post> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Post>(`${this.baseUrl}/${id}`, post, { headers });
  }
}
