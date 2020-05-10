import { TestBed } from '@angular/core/testing';

import { AuthInitializer } from './auth-initializer.service';

describe('AuthInitializer', () => {
    let service: AuthInitializer;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(AuthInitializer);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
