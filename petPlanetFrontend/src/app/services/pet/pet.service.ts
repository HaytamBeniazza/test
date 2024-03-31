import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pet } from 'src/app/Interfaces/Pet';

@Injectable({
  providedIn: 'root'
})
export class PetService {

  private baseUrl = 'http://localhost:8080/pet';

  constructor(private http: HttpClient) { }

  addPet(pet: Pet): Observable<Pet> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Pet>(`${this.baseUrl}/add`, pet, { headers });
  }

  getAllPets(page: number, size: number): Observable<Pet[]> {
    return this.http.get<Pet[]>(`${this.baseUrl}/list?page=${page}&size=${size}`);
  }

  getPetById(id: number): Observable<Pet> {
    return this.http.get<Pet>(`${this.baseUrl}/id/${id}`);
  }

  getPetByName(name: string): Observable<Pet> {
    return this.http.get<Pet>(`${this.baseUrl}/name/${name}`);
  }

  deletePet(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  updatePet(id: number, pet: Pet): Observable<Pet> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Pet>(`${this.baseUrl}/${id}`, pet, { headers });
  }
}
