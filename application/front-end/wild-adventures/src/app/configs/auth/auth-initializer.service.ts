import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { KeycloakService } from 'src/app/shared/services/keycloak/keycloak.service';
import { filter } from 'rxjs/operators';
import { KeycloakEventType } from 'keycloak-angular';
import { CartContentService } from 'src/app/shared/services/cart-content/cart-content.service';

@Injectable({
    providedIn: 'root',
})
export class AuthInitializer {
    constructor(private keycloakService: KeycloakService, private cartService: CartContentService) {
        if (!environment.production) {
            Object.assign(globalThis, { keycloakService });
        }
    }

    init(): Promise<any> {
        return this.keycloakService
            .init({
                ...environment.keycloak_angular,
                loadUserProfileAtStartUp: true,
                initOptions: {
                    onLoad: 'check-sso',
                    checkLoginIframe: false,
                },
            })
            .then((isLoggedIn) => {
                if (isLoggedIn) {
                    this.keycloakService.keycloakEvents$
                        .pipe(filter((e) => e.type === KeycloakEventType.OnTokenExpired))
                        .subscribe(() => this.keycloakService.updateToken());
                    this.cartService.update();
                }
            });
    }
}
