import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-skeleton',
  standalone: true,
  imports: [CommonModule, HeaderComponent],
  templateUrl: './app-skeleton.component.html',
  styleUrls: ['./app-skeleton.component.scss']
})
export class AppSkeletonComponent {

}
