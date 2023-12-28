import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Gender } from '../../models/gender.enum';
import { Teacher } from '../../models/teacher.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-teacher-form',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  templateUrl: './teacher-form.component.html',
  styleUrl: './teacher-form.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TeacherFormComponent implements OnInit {
  @Input()
  teacher = new Teacher();
  @Input()
  isAddPage: boolean = false;
  @Input()
  subscription: Subscription;
  @Output()
  onSubmit: EventEmitter<Teacher> = new EventEmitter();
  @Output()
  onDelete: EventEmitter<Teacher> = new EventEmitter();
  @Output()
  onCancel: EventEmitter<void> = new EventEmitter();

  public teacherForm: FormGroup;
  public gender = Gender;
  private showValidationErrors = false;

  constructor(private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.buildForm();
  } 

  onCreate() {
    this.showValidationErrors = true;
    if (!this.teacherForm.valid) {
      return;
    }
    const teacherFormValue = this.teacherForm.value;
    const newTeacher = new Teacher().deserialize({
      firstName: teacherFormValue.firstName,
      lastName: teacherFormValue.lastName,
      gender: teacherFormValue.gender,
      email: teacherFormValue.email,
      teacherLesson: {
        name: teacherFormValue.teacherLesson
      },
    });

    this.onSubmit.emit(newTeacher);
  }

  handleOnDelete() {
    this.onDelete.emit(this.teacher);
  }

  handleOnCancel() {
    this.onCancel.emit();
  }

  get teacherFormControl() {
    return this.teacherForm.controls;
  }

  get hasErrorFirstName() {
    return (this.teacherFormControl.firstName.touched || this.showValidationErrors) && this.teacherFormControl.firstName.errors?.required;
  }

  get hasErrorLastName() {
    return (this.teacherFormControl.lastName.touched || this.showValidationErrors) && this.teacherFormControl.lastName.errors?.required;
  }

  get hasErrorGender() {
    return (this.teacherFormControl.gender.touched || this.showValidationErrors) && this.teacherFormControl.gender.errors?.required;
  }

  get hasErrorEmail() {
    return (this.teacherFormControl.email.touched || this.showValidationErrors) && this.teacherFormControl.email.errors?.required;
  }

  get loading() {
    return this.subscription && !this.subscription.closed;
  }

  private buildForm(): void {
    const controls = {
      firstName: [this.teacher.firstName, [Validators.required]],
      lastName: [this.teacher.lastName, [Validators.required]],
      gender: [this.teacher.gender, [Validators.required]],
      email: [this.teacher.email, [Validators.required]],
      teacherLesson: [this.teacher.teacherLesson?.name]
    }

    this.teacherForm = this.formBuilder.group(controls);
  }
}
