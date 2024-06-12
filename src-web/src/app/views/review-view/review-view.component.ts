import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReviewService } from 'src/app/services/ReviewService/review-service.service';
import { ReviewCompleta } from 'src/app/entities/reviewCompleta';
import { ReviewCompletaComponent } from 'src/app/components/review-completa/review-completa.component';

@Component({
  selector: 'app-review-view',
  standalone: true,
  imports: [CommonModule, ReviewCompletaComponent],
  templateUrl: './review-view.component.html',
  styleUrls: ['./review-view.component.scss']
})
export class ReviewViewComponent {

  reviews: ReviewCompleta[] = []

  constructor(private servicioReview: ReviewService) {
    //Obtenemos las reseñas
    servicioReview.getReviews().subscribe((reviews => {
      this.reviews = reviews
    }))
  }

}
