import { Observable } from 'rxjs';

export interface HttpService<Identifier, Entity> {
    findAll(params?: any): Observable<Entity[]>;
    findOne(Identifier, params?: any): Observable<Entity>;
    create(Entity, params?: any): Observable<Entity>;
    update(Identifier, Entity, params?: any): Observable<Entity>;
    delete(Identifier, params?: any): Observable<void>;
}
