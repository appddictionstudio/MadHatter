import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { tap } from 'rxjs/operators';
import { SnotifyService, SnotifyPosition } from 'ng-snotify';
import { environment } from 'src/environments/environment';
import { AppComponent } from 'src/app/app.component';
import { UseExistingWebDriver } from 'protractor/built/driverProviders';
import { strict } from 'assert';
import { flattenStyles } from '@angular/platform-browser/src/dom/dom_renderer';

@Injectable()
export class BaseInterceptor implements HttpInterceptor {

  constructor(
    public auth: AuthService,
    public app: AppComponent,
    private snotifyService: SnotifyService,
    ) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let authReq;

    if (!this.auth.isAuthenticated()) {
      authReq = req.clone({headers: req.headers
        .set('Cache-control', 'no-cache')
        .set('Pragma', 'no-cache')
        .set('Content-Type', 'application/json')
      });
    } else {
      authReq = req.clone({headers: req.headers
        .set('Cache-control', 'no-cache')
        .set('Pragma', 'no-cache')
        .set('Authorization', 'Bearer ' + this.auth.getToken())});
      }

    // Pass on the cloned request instead of the original request.
    return next.handle(authReq).pipe(
      tap(
        event => event instanceof HttpResponse ? 'succeeded' : '',
        error => {
          if (error instanceof HttpErrorResponse) {
            if (error.url.includes('signin')) {
              console.log(error);
              return false;
            } if (error.status === 401 && !error.url.includes('api/auth/signin')) {
              this.auth.destroyToken();
              location.reload();
            } else {
              this.snotifyService.error(error.status + ' - ' + error.statusText, 'Error', {
                timeout: 4000,
                showProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                position: SnotifyPosition.rightBottom
              });
            }
          }
        })
    );
  }
}
