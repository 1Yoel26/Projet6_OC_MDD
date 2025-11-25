import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Article } from '../interfaces/article.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private httpClient: HttpClient) { }
  
    private cheminHttp: string = "/api/article/creationArticle";
    
    public creationArticle(infoArticle: Article): Observable<void>{
      return this.httpClient.post<void>(this.cheminHttp, infoArticle);
    }
}
