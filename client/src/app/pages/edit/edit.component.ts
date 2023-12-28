import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { Router } from '@angular/router';
import { Teacher } from '../../models/teacher.model';
import { TeacherFormComponent } from '../../shared/teacher-form/teacher-form.component';
import { Subscription, noop } from 'rxjs';
import { TeacherService } from '../../services/teacher.service';
import { TitleComponent } from '../../shared/title/title.component';

@Component({
  selector: 'app-edit',
  standalone: true,
  imports: [
    CommonModule,
    TeacherFormComponent,
    TitleComponent
  ],
  templateUrl: './edit.component.html',
  styleUrl: './edit.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EditComponent {
  teacher: Teacher;
  editTeacherSubscription: Subscription;
  serverError: string;

  constructor(private router: Router, private teacherService: TeacherService) {
    const state = this.router.getCurrentNavigation()?.extras?.state;
    if (state) {
      this.teacher = state['teacher'];
    }
  }

  handleOnSubmit(teacher: Teacher) {
    this.editTeacherSubscription = this.teacherService.updateTeacher({teacherDTO: teacher, email: this.teacher.email})
      .subscribe(() => this.router.navigate(['list']), noop);
  }

  handleCancel() {
    this.router.navigate(['list']);
  }
}
