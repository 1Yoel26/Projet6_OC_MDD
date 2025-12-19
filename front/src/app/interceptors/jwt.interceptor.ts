import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    const token = localStorage.getItem("token");

    // si le token jwt existe, ajoute le en header à chaque requette Http:
    if(token){
      const requeteClone = request.clone({
        headers : request.headers.set("Authorization", `Bearer ${token}`)
      });

      return next.handle(requeteClone).pipe(
        catchError((erreur: HttpErrorResponse)=>{
          if(erreur.status === 401){
            // Si le jwt est invalide, le supprimer du LocalStorage:
          localStorage.removeItem('token');

          }

          return throwError(() => erreur);
        })
      );
    }

    return next.handle(request);
  }
}
