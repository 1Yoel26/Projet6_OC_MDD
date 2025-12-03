import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Article } from '../interfaces/article.interface';
import { Observable } from 'rxjs';
import { ArticleComplet } from '../interfaces/articleComplet.interface';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  constructor(private httpClient: HttpClient) { }
  
    private cheminHttp: string = "/api/article/";
    
    public creationArticle(infoArticle: Article): Observable<void>{
      return this.httpClient.post<void>(this.cheminHttp + "creationArticle", infoArticle);
    }

    public listeDesArticles(): Observable<ArticleComplet[]>{
        return this.httpClient.get<ArticleComplet[]>(this.cheminHttp + "listeDesArticles");
    }


    public listeDesArticlesAbonneRecent(): Observable<ArticleComplet[]>{
        return this.httpClient.get<ArticleComplet[]>(this.cheminHttp + "listeDesArticlesAbonneRecent");
    }


    public listeDesArticlesAncien(): Observable<ArticleComplet[]>{
        return this.httpClient.get<ArticleComplet[]>(this.cheminHttp + "listeDesArticlesAncien");
    }


    public listeDesArticlesAbonneAncien(): Observable<ArticleComplet[]>{
        return this.httpClient.get<ArticleComplet[]>(this.cheminHttp + "listeDesArticlesAbonneAncien");
    }


    public article(idArticle: number): Observable<ArticleComplet>{
      return this.httpClient.get<ArticleComplet>(this.cheminHttp + idArticle);
    }
}
