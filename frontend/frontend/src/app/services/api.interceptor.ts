import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
} from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { UtilService } from './util.service';

@Injectable()
export class ApiInterceptor implements HttpInterceptor {
  constructor(private _utilService: UtilService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    if (!request.url.includes('login')) {
      if (request.method === 'POST' || request.method === 'GET') {
        const _req = request.clone({
          headers: request.headers.set(
            'Authorization',
            this._utilService.getToken()
          ),
        });
        return next.handle(_req).pipe(
          map((event) => {
            return event;
          })
        );
      }
    }
    return next.handle(request).pipe(
      map((event) => {
        return event;
      })
    );
  }
}
