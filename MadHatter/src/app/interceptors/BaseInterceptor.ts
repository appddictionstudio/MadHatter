import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable()
export class BaseInterceptor implements HttpInterceptor {

  constructor(public auth: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    // Set the cache control headers needed to prevent IE from caching the
    // // response from get API calls
    // let headers = new HttpHeaders({
    //   'Cache-control': 'no-cache',
    //   'Pragma': 'no-cache'
    // });

    let authReq;

    if (!this.auth.isAuthenticated()) {
      authReq = req.clone({headers: req.headers
        .set('Cache-control', 'no-cache')
        .set('Pragma', 'no-cache')
        .set('Content-Type', 'application/x-www-form-urlencoded')
        // .set('Authorization', environment.client)
      });
    } else {
      authReq = req.clone({headers: req.headers
        .set('Cache-control', 'no-cache')
        .set('Pragma', 'no-cache')
        // .set('Content-Type', 'application/x-www-form-urlencoded')
        .set('Authorization', 'Bearer ' + this.auth.getToken())})
    }

    // Pass on the cloned request instead of the original request.
    return next.handle(authReq).pipe(
      tap(
        event => event instanceof HttpResponse ? 'succeeded' : '',
        error => {
          if (error instanceof HttpErrorResponse) {
            this.auth.setRedirectLocation();
            this.auth.redirectToLogin();
            console.log('http error intercepted. redirect location: ' + this.auth.getRedirectLocation())
          }
        })
    );
  }
}
