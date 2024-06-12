import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Review } from 'src/app/entities/review';
import { ReviewCompleta } from 'src/app/entities/reviewCompleta';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'review',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.scss']
})
export class ReviewComponent {
  @Input()
  reviewData?: Review;

  @Input()
  gameInfo?: {
    nombre: string,
    src: File
  };

  constructor(private sanitizer: DomSanitizer){ }

  getSrcImagen(imagen: File){
    if(!imagen){
      return '../../../assets/defaultUser.jpg'
    }

    return this.sanitizer.bypassSecurityTrustUrl("data:image/png;base64," + imagen);
  }

  getRange(total: number): number[] {
    return Array(total).fill(0).map((x, i) => i);
  }
}
