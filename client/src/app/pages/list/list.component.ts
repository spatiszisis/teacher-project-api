import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Observable, Subject, Subscription, debounceTime, noop, switchMap, tap } from 'rxjs';
import { SortDirection } from '../../models/sort-direction.enum';
import { Teacher } from '../../models/teacher.model';
import { TeacherService } from '../../services/teacher.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { TitleComponent } from '../../shared/title/title.component';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink,
    FormsModule,
    ReactiveFormsModule,
    TitleComponent
  ],
  templateUrl: './list.component.html',
  styleUrl: './list.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ListComponent { 

  teachers$: Observable<Teacher[]>;
  selectedTeacher: Teacher;
  searchTerm: Subject<string> = new Subject();
  search$: Observable<string> = this.searchTerm.asObservable();
  isSearching = false;
  updateTeacherSubscription: Subscription;
  selectedPage = 0;
  selectPageSize = 4;
  selectedSortOption = '';
  pages$: Observable<number[]>;
  serverError: string;
  sortDirection: SortDirection = "DEFAULT";

  constructor(private router: Router, private teacherService: TeacherService, private toastr: ToastrService) {}

  ngOnInit(): void {
    this.teachers$ = this.teacherService.teachers$;
    this.pages$ = this.teacherService.pages$;

    this.search$.pipe(
      tap(() => this.clearError()),
      tap(() => this.isSearching = true),
      debounceTime(500),
      switchMap(searchTerm => !!searchTerm ? this.teacherService.searchTeacher(searchTerm) : this.teacherService.getAllTeachers())
    ).subscribe(() => this.isSearching = false);
  }

  handleOnDelete(teacher: Teacher) {
    this.teacherService.deleteTeacher(teacher).subscribe(() => this.clearTeacher(), noop);
  }

  handleOnCancel() {
    this.clearTeacher();
  }

  onEditTeacher(teacher: Teacher) {
    this.clearError();
    this.teacherService.getTeacher(teacher).subscribe((teacher: Teacher) => {
      this.selectedTeacher = teacher;
    });
  }

  onSearch(event: Event) {
    const inputText = event?.target['value'];
    this.searchTerm.next(inputText);
  }

  onClearSearch() {
    this.searchTerm.next('');
  }

  onSortByDirection(sortProperty: string, sortDirection: SortDirection) {
    this.clearError();
    if (this.sortDirection === 'DEFAULT') {
      this.sortDirection = 'ASC';
    }
    this.sortDirection = sortDirection;
    this.selectedSortOption = sortProperty;
    this.teacherService.getAllTeachers(this.selectedPage, this.selectPageSize, this.selectedSortOption, this.sortDirection).subscribe(noop, noop);
  }

  handleClickPage(page: number, event: Event) {
    event.preventDefault();
    this.clearError();
    this.selectedPage = page;
    this.teacherService.getAllTeachers(this.selectedPage, this.selectPageSize, this.selectedSortOption, this.sortDirection).subscribe(noop, noop);
  }

  onChangeSizePage(sizePage: any) {
    this.clearError();
    this.selectPageSize = sizePage.target.value;
    this.teacherService.getAllTeachers(0, this.selectPageSize, this.selectedSortOption, this.sortDirection).subscribe(noop, noop);
  }

  isASCSortOption(sortOption: string) {
    if (sortOption === 'ASC') {
      return true;
    }

    return false;
  }

  isDESCSortOption(sortOption: string) {
    if (sortOption === 'DESC') {
      return true;
    }

    return false;
  }

  editTeacher(teacher: Teacher) {
    this.router.navigate(['edit'], {
      state: {
        teacher: teacher
      },
    });
  }

  private clearTeacher() {
    this.selectedTeacher = undefined;
    this.selectedPage = 0;
    this.teacherService.getAllTeachers(this.selectedPage, this.selectPageSize, this.selectedSortOption, this.sortDirection).subscribe(noop, noop);
  }

  navigateToAddPage() {
    this.router.navigate(['add']);
  }

  private clearError() {
    this.serverError = undefined;
  }
}
