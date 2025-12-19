import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserInscription } from '../interfaces/userInscription.interface';
import { Observable } from 'rxjs';
import { UserConnection } from '../interfaces/userConnection.interface';
import { UserInfo} from '../interfaces/userInfo.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {}

  private cheminHttp: string = "api";

  public inscription(infoUser: UserInscription): Observable<void>{
    return this.httpClient.post<void>(this.cheminHttp + "/inscription", infoUser);
  }

  public connection(infoUser: UserConnection): Observable<{token : string}>{
    return this.httpClient.post<{token : string}>(this.cheminHttp + "/connection", infoUser);
  }


  public infoUser(): Observable<UserInfo>{
    return this.httpClient.get<UserInfo>(this.cheminHttp + "/user/infoUserConnecte");
  }

  public modificationInfoUser(infoModifProfil: UserInscription): Observable<void>{
    return this.httpClient.put<void>(this.cheminHttp + "/user/modificationInfoUserConnecte", infoModifProfil);
  }



  
}
