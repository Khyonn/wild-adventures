import { Injectable } from '@angular/core';
import { KeycloakService as OKeycloakService, KeycloakOptions, KeycloakConfig } from 'keycloak-angular';

@Injectable({
    providedIn: 'root',
})
export class KeycloakService extends OKeycloakService {
    isServerUp = false;
    constructor() {
        super();
    }

    async init(options: KeycloakOptions): Promise<boolean> {
        try {
            const { url, realm } = options.config as KeycloakConfig;

            // Le fait de rajouter ce check va nous permettre de vérifier que le server Keycloak soit up
            await fetch(`${url}/realms/${realm}/.well-known/openid-configuration`);
            this.isServerUp = true;
            return super.init(options);
        } catch (e) {
            console.warn('Keycloak server is down');
            return false;
        }
    }

    async isLoggedIn(): Promise<boolean> {
        if (!this.isServerUp) {
            return false;
        }
        return super.isLoggedIn();
    }

    async getUserId(): Promise<string> {
        if (await this.isLoggedIn()) {
            return JSON.parse(atob((await super.getToken()).split('.')[1])).sub;
        }
        return null;
    }
}
