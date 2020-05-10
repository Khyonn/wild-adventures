import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { from, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { KeycloakService } from '../services/keycloak/keycloak.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
    constructor(private keycloakService: KeycloakService) {}

    isUrlExcluded({ method, url }, { urlPattern, httpMethods }) {
        const httpTest = httpMethods.length === 0 || httpMethods.join().indexOf(method.toUpperCase()) > -1;
        const urlTest = urlPattern.test(url);
        return httpTest && urlTest;
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const { enableBearerInterceptor, excludedUrls } = this.keycloakService;
        if (!enableBearerInterceptor) {
            return next.handle(req);
        }
        const shallPass = excludedUrls.findIndex((item: any) => this.isUrlExcluded(req, item)) > -1;
        if (shallPass) {
            return next.handle(req);
        }
        return from(this.keycloakService.isLoggedIn()).pipe(
            mergeMap((loggedIn) => (loggedIn ? this.handleRequestWithTokenHeader(req, next) : next.handle(req)))
        );
    }

    handleRequestWithTokenHeader(req: HttpRequest<any>, next: HttpHandler) {
        return this.keycloakService.addTokenToHeader(req.headers).pipe(
            mergeMap((headersWithBearer) => {
                const kcReq = req.clone({ headers: headersWithBearer });
                return next.handle(kcReq);
            })
        );
    }
}
