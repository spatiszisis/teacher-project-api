import { CommonModule, Location } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-title',
  standalone: true,
  imports: [
    CommonModule,
  ],
  template: `
    <span class="flex items-center mb-4">
        <svg (click)="goBack()" class="w-4 h-4 text-gray-800 dark:text-white cursor-pointer" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 8 14">
          <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 1 1.3 6.326a.91.91 0 0 0 0 1.348L7 13"/>
        </svg>
        <h2 class="ml-4 text-xl font-bold text-gray-900 dark:text-white">{{title}}</h2>
    </span>
  `,
  styleUrl: './title.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TitleComponent {
  @Input()
  title: string;

  constructor(private router: Router) {}

  goBack() {
    this.router.navigate(['intro']);
  }
}
