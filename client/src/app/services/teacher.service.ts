import { Injectable } from '@angular/core';
import { Teacher } from '../models/teacher.model';
import { BehaviorSubject, EMPTY, Observable, catchError, map, mergeMap, of, tap, throwError } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { SortDirection } from '../models/sort-direction.enum';
import { UpdateCustomerResource } from '../models/update-customer-resource.model';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class TeacherService {
  protected readonly BASE_PATH = '/api/teacher';
  private teachers: BehaviorSubject<Teacher[]> = new BehaviorSubject<Teacher[]>([]);
  public teachers$: Observable<Teacher[]> = this.teachers.asObservable();

  private teacher: BehaviorSubject<Teacher> = new BehaviorSubject<Teacher>(new Teacher());
  public teacher$: Observable<Teacher> = this.teacher.asObservable();

  private pages: BehaviorSubject<number[]> = new BehaviorSubject<number[]>([]);
  public pages$: Observable<number[]> = this.pages.asObservable();

  constructor(private http: HttpClient, private toastr: ToastrService) { }

  public getAllTeachers(pageNumber: number = 0, pageSize: number = 4, sortProperty: string = '', sortDirection: SortDirection = 'DEFAULT'): Observable<Teacher[]> {
    let params: HttpParams = new HttpParams().set('pageNumber', pageNumber).set('pageSize', pageSize);
    if (!!sortProperty && !!sortDirection) {
      params = params.set('sortProperty', sortProperty).set('sortDirection', sortDirection);
    }
    return this.http.get<Teacher[]>(`${this.BASE_PATH}/all`, { params }).pipe(
      map((json: any) => json),
      tap((json: any) => this.teachers.next(json.content)),
      tap((json: any) => this.pages.next(new Array(json.totalPages))),
      catchError((error) => {
        this.toastr.error(error.error.message);
        return throwError(EMPTY);
      })
    );
  }

  public getTeacher(teacher: Teacher): Observable<Teacher> {
    let params!: HttpParams;
    if (!!teacher.email) {
      params = new HttpParams().set('searchTerm', teacher.email);
    }
    return this.http.get(`${this.BASE_PATH}`, { params }).pipe(
      map((json: any) => json),
      tap((teacher: Teacher) => this.teacher.next(teacher)),
      catchError((error) => {
        this.toastr.error(error.error.message);
        return throwError(EMPTY);
      })
    );
  }

  public searchTeacher(searchTerm?: string) {
    let params!: HttpParams;
    if (!!searchTerm) {
      params = new HttpParams().set('searchTerm', searchTerm);
    }
    return this.http.get<Teacher[]>(`${this.BASE_PATH}/search`, { params }).pipe(
      map((json: Teacher[]) => json),
      tap((teachers: Teacher[]) => this.teachers.next(teachers)),
      catchError((error) => {
        this.toastr.error(error.error.message);
        return throwError(EMPTY);
      })
    );
  }

  public createUser(teacher: Teacher): Observable<Teacher> {
    return this.http.post(this.BASE_PATH, teacher).pipe(
      map((json: any) => json),
      mergeMap((teacher: Teacher) => this.getAllTeachers().pipe(map(() => teacher))),
      tap((latestTeacher) => this.toastr.success(`The teacher ${latestTeacher.firstName} ${latestTeacher.lastName} has been created.`)),
      catchError((error) => {
        this.toastr.error(error.error.message);
        return throwError(EMPTY);
      })
    );
  }

  public updateTeacher(updateCustomerResource: UpdateCustomerResource): Observable<Teacher> {
    return this.http.put<Teacher>(`${this.BASE_PATH}`, updateCustomerResource).pipe(
      map((json: any) => json),
      mergeMap((teacher: Teacher) => this.getAllTeachers().pipe(map(() => teacher))),
      tap((latestTeacher) => this.toastr.success(`The teacher ${latestTeacher.firstName} ${latestTeacher.lastName} has been updated.`)),
      catchError((error) => {
        this.toastr.error(error.error.message);
        return throwError(EMPTY);
      })
    );
  }

  public deleteTeacher(teacher: Teacher): Observable<any> {
    let params!: HttpParams;
    if (!!teacher.email) {
      params = new HttpParams().set('email', teacher.email);
    }
    return this.http.delete(`${this.BASE_PATH}`, { params }).pipe(
      mergeMap(() => this.getAllTeachers().pipe(map(() => null))),
      tap(() => this.toastr.success(`The teacher ${teacher.firstName} ${teacher.lastName} has been deleted.`)),
      catchError((error) => {
        this.toastr.error(error.error.message);
        return throwError(EMPTY);
      })
    );
  }

}
