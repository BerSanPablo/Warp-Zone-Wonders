import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReviewComponent } from '../review/review.component';
import { ReviewCompleta } from 'src/app/entities/reviewCompleta';
import { DomSanitizer } from '@angular/platform-browser';
import { Review } from 'src/app/entities/review';

@Component({
  selector: 'review-completa',
  standalone: true,
  imports: [CommonModule, ReviewComponent],
  templateUrl: './review-completa.component.html',
  styleUrls: ['./review-completa.component.scss']
})
export class ReviewCompletaComponent {

  @Input()
  reviewData?: ReviewCompleta;

  constructor(private sanitizer: DomSanitizer){ }

  getSrcImagen(){
    if(!this.reviewData || !this.reviewData.imagenUsuario){
      return '../../../assets/imagePlaceholder.png'
    }

    return this.sanitizer.bypassSecurityTrustUrl("data:image/png;base64," + this.reviewData.imagenVideojuego);
  }

  getGameInfo(): {nombre: string, src: File} {
    return {
      nombre: this.reviewData!.nombreVideojuego,
      src: this.reviewData!.imagenVideojuego!
    }
  }

  getSimpleReviewData(): Review{
    return {
      nombreUsuario: this.reviewData!.nombreUsuario,
      imagenUsuario: this.reviewData!.imagenUsuario,
      valoracion: this.reviewData!.valoracion,
      comentario: this.reviewData!.comentario,
    }
  }
}
