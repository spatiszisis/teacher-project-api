import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-intro',
  standalone: true,
  imports: [
    CommonModule,
    RouterLink
  ],
  templateUrl: './intro.component.html',
  styleUrl: './intro.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class IntroComponent { }
