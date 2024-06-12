import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VideojuegoDTO } from 'src/app/entities/videojuego-dto';
import { VideoGameService } from 'src/app/services/VideoGameService/video-game-service.service';
import { VideoGameCardComponent } from 'src/app/components/video-game-card/video-game-card.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-video-game-view',
  standalone: true,
  imports: [CommonModule, VideoGameCardComponent, FormsModule],
  templateUrl: './video-game-view.component.html',
  styleUrls: ['./video-game-view.component.scss']
})
export class VideoGameViewComponent {
  videojuegos:VideojuegoDTO[] = [];
  textoBusqueda = ''

  constructor(private servicioVideojuego:VideoGameService) {
    servicioVideojuego.getVideojuegos().subscribe(videojuegos => {
      this.videojuegos = videojuegos;
    })
  }

  buscar(textoBusqueda: string){
    this.servicioVideojuego.getVideojuegosFiltradoParcial(textoBusqueda).subscribe(videojuegos => {
      this.videojuegos = videojuegos;
    })
  }
}
