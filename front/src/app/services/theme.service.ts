import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Theme } from '../interfaces/theme.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  constructor(private httpClient: HttpClient) { }

  private cheminHttp: string = "/api/theme/lesThemes";
  
    public lesThemes(): Observable<Theme[]>{
      return this.httpClient.get<Theme[]>(this.cheminHttp);
    }
}
