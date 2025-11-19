import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserInscription } from '../interfaces/userInscription.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {}

  private cheminHttp: string = "api/inscription";

  public inscription(infoUser: UserInscription): Observable<void>{
    return this.httpClient.post<void>(this.cheminHttp, infoUser)
  }



  
}
