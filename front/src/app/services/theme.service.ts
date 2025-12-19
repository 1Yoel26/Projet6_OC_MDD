import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Theme } from '../interfaces/theme.interface';
import { Observable } from 'rxjs';
import { Abonnement } from '../interfaces/abonnement.interface';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  constructor(private httpClient: HttpClient) { }

  private cheminHttp: string = "/api/theme";
  
    public lesThemes(): Observable<Theme[]>{
      return this.httpClient.get<Theme[]>(this.cheminHttp + "/lesThemes");
    }

    public lesIdThemesAbonne(): Observable<number[]>{
      return this.httpClient.get<number[]>(this.cheminHttp + "/lesIdThemesAbonne");
    }

    public lesThemesAbonne(): Observable<Abonnement[]>{
      return this.httpClient.get<Abonnement[]>(this.cheminHttp + "/lesThemesAbonne");
    }


    public abonnement(idTheme: number): Observable<void>{
      return this.httpClient.post<void>(this.cheminHttp + "/abonnementAunTheme/" + idTheme, {});
    }

    public desabonnement(idTheme: number): Observable<void>{
      return this.httpClient.delete<void>(this.cheminHttp + "/desabonnementAunTheme/" + idTheme);
    }

}
