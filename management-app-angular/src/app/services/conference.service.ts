import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface Conference {
  id: number;
  title: string;
  type: string;
  date: string;
  keynoteIds: number[];
}

@Injectable({
  providedIn: 'root'
})
export class ConferenceService {
  private apiUrl = 'http://localhost:8082/api/conferences';

  constructor(private http: HttpClient) {}

  getAllConferences(): Observable<Conference[]> {
    return this.http.get<any>(this.apiUrl).pipe(
      map(res => res._embedded ? res._embedded.conferences : res)
    );
  }

  getConferenceById(id: number): Observable<Conference> {
    return this.http.get<Conference>(`${this.apiUrl}/${id}`);
  }



  // ✅ Supprimer une conférence par son ID
  deleteConference(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}
