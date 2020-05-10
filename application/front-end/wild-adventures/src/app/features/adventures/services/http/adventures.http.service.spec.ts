import { TestBed } from '@angular/core/testing';

import { AdventuresHttpService } from './adventures.http.service';

describe('AdventuresHttpService', () => {
    let service: AdventuresHttpService;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(AdventuresHttpService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
