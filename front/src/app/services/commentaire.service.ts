import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Commentaire } from '../interfaces/commentaire.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentaireService {

   constructor(private httpClient: HttpClient) { }
  
    private cheminHttp: string = "/api/commentaire";
    
    public creationCommentaire(infoCommentaire: Commentaire): Observable<void>{
      return this.httpClient.post<void>(this.cheminHttp + "/creationCommentaire", infoCommentaire);
    }

    public listeDesCommentaires(idArticle: number): Observable<Commentaire[]>{
      return this.httpClient.get<Commentaire[]>(this.cheminHttp + "/listeDesCommentaires/" + idArticle);
    }
}
