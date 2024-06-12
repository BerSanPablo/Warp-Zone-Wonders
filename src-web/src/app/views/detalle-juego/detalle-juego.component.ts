import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { VideojuegoDTO } from 'src/app/entities/videojuego-dto';
import { VideoGameService } from 'src/app/services/VideoGameService/video-game-service.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ReviewService } from 'src/app/services/ReviewService/review-service.service';
import { ReviewComponent } from 'src/app/components/review/review.component';
import { Review } from 'src/app/entities/review';
import { UserService } from 'src/app/services/UserService/user-service.service';

@Component({
  selector: 'app-detalle-juego',
  standalone: true,
  imports: [CommonModule, ReviewComponent],
  templateUrl: './detalle-juego.component.html',
  styleUrls: ['./detalle-juego.component.scss']
})
export class DetalleJuegoComponent {

  videojuegoDetails: VideojuegoDTO = {
    nombre: '',
    fechaCreacion: new Date(),
    imagenPortada: null,
    sinopsis: '',
    tags: []
  }

  reviews: Review[] = []

  constructor(protected usuarioService: UserService, private servicioVideojuego: VideoGameService, private servicioReview: ReviewService, private route: ActivatedRoute, private router: Router, private sanitizer: DomSanitizer) {
    const nombre = this.route.snapshot.paramMap.get('nombre')

    if(nombre == null){
      this.router.navigate(['/']);
      return
    }

    //Guardamos el nombre
    this.videojuegoDetails.nombre = nombre

    //Obtenemos el resto de los datos
    servicioVideojuego.getVideojuegoByName(nombre).subscribe((videojuego => {
      this.videojuegoDetails.fechaCreacion = videojuego.fechaCreacion
      this.videojuegoDetails.imagenPortada = videojuego.imagenPortada
      this.videojuegoDetails.sinopsis = videojuego.sinopsis
      this.videojuegoDetails.tags = videojuego.tags
    }))

    //Obtenemos las reseñas
    servicioReview.getReviewsByVideojuego(nombre).subscribe((reviews => {
      this.reviews = reviews
    }))
  }

  sanitizeImageBlob(archivo: File | null){
    if(!archivo){
      return null
    }
    return this.sanitizer.bypassSecurityTrustUrl("data:image/png;base64," + archivo);
  }

}
