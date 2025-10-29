import {Injectable} from "@angular/core";
import {environment} from "../environnement/environnement";
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of} from "rxjs";
import {Router} from "@angular/router";

@Injectable()
export default abstract class BaseService<ENTITY, ENTITY_CREATE_INPUT> {

  protected baseUrl = environment.apiUrl;

  constructor(protected http: HttpClient, protected router : Router) {}

  abstract getEndpointUrl() : string;

  protected handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(`${operation} failed: ${error.message}`, error); // log to console instead

      this.router.navigate(['/error']);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  getAll(): Observable<ENTITY[]> {
    return this.http.get<ENTITY[]>(`${this.baseUrl}${this.getEndpointUrl()}`)
      .pipe(
        catchError(this.handleError<ENTITY[]>('getAll', []))
      );
  }

  getById(id: string): Observable<ENTITY> {
    return this.http.get<ENTITY>(`${this.baseUrl}${this.getEndpointUrl()}/${id}`)
      .pipe(
        catchError(this.handleError<ENTITY>('getById', undefined))
      );
  }

  delete(entity: ENTITY & { id: string }): Observable<boolean> {
    return this.http.delete<boolean>(`${this.baseUrl}${this.getEndpointUrl()}/${entity.id}`)
      .pipe(
        catchError(this.handleError<boolean>('delete', false))
      );
  }

  create(entity: ENTITY_CREATE_INPUT): Observable<ENTITY> {
    return this.http.post<ENTITY>(`${this.baseUrl}${this.getEndpointUrl()}`, entity)
      .pipe(
        catchError(this.handleError<ENTITY>('create', undefined))
      );
  }
}
