import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserInscription } from '../interfaces/userInscription.interface';
import { Observable } from 'rxjs';
import { UserConnection } from '../interfaces/userConnection.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {}

  private cheminHttp: string = "api/inscription";

  public inscription(infoUser: UserInscription): Observable<void>{
    return this.httpClient.post<void>(this.cheminHttp, infoUser)
  }

  
  private cheminHttp2: string = "api/connection";

  public connection(infoUser: UserConnection): Observable<{token : string}>{
    return this.httpClient.post<{token : string}>(this.cheminHttp2, infoUser)
  }



  
}
