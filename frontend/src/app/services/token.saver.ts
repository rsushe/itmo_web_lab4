import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';

import {StorageService} from './storage.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class TokenInjector implements HttpInterceptor {

  constructor(private token: StorageService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authReq = req;
    let token = null;
    if (this.token.getToken()) {
      token = "Bearer " + this.token.getToken();
    }

    console.log("token info:" + token);
    if (token != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, token)});
    }
    return next.handle(authReq);
  }
}

export const httpInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: TokenInjector, multi: true}
];