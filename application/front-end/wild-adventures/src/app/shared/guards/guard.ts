import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';

@Injectable({
    providedIn: 'root',
})
export class RoleGuard implements CanActivate {
    constructor(private keycloakService: KeycloakService) {
        keycloakService.isLoggedIn();
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        return new Promise<boolean>(async (resolve, reject) => {
            if (!this.keycloakService.isServerUp) {
                return reject(false);
            }
            if (!(await this.keycloakService.isLoggedIn())) {
                this.keycloakService
                    .login({
                        redirectUri: `${globalThis.location.origin}/#${state.url}`,
                    })
                    .catch((e) => console.error(e));
                return reject(false);
            }

            const { verification } = route.data as { verification: Verification };
            const { hasRealmRole } = this.keycloakService.getKeycloakInstance();
            if (!Array.isArray(verification && verification.roles)) {
                reject(false);
            }

            let result: boolean;
            switch (verification.type) {
                case VerificationType.ALL:
                    result = verification.roles.every(hasRealmRole);
                    break;
                case VerificationType.SOME:
                    result = verification.roles.some(hasRealmRole);
                    break;
                default:
                    reject(false);
            }
            result ? resolve(result) : reject(result);
        });
    }
}

export enum VerificationType {
    ALL = 'ALL_ROLES',
    SOME = 'SOME_ROLES',
}

export class Verification {
    constructor(public roles: string[] = [], public type: VerificationType = VerificationType.ALL) {}
}
