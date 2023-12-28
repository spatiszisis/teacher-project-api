import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { TeacherFormComponent } from '../../shared/teacher-form/teacher-form.component';
import { Subscription, delay, noop } from 'rxjs';
import { TeacherService } from '../../services/teacher.service';
import { Router } from '@angular/router';
import { Teacher } from '../../models/teacher.model';
import { ToastrService } from 'ngx-toastr';
import { TitleComponent } from '../../shared/title/title.component';

@Component({
  selector: 'app-add',
  standalone: true,
  imports: [
    CommonModule,
    TeacherFormComponent,
    TitleComponent
  ],
  templateUrl: './add.component.html',
  styleUrl: './add.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AddComponent {
  saveTeacherSubscription: Subscription;
  serverError: string;

  constructor(private teacherService: TeacherService, private router: Router, private toastr: ToastrService) { }

  handleOnSubmit(teacher: Teacher) {
    this.teacherService.createUser(teacher).subscribe(() => this.router.navigate(['list']), noop);
  }

  handleCancel() {
    this.router.navigate(['intro']);
  }
}
