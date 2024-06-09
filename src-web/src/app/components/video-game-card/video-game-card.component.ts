import { DomSanitizer } from '@angular/platform-browser';
import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VideojuegoDTO } from 'src/app/entities/videojuego-dto';

@Component({
  selector: 'video-game-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './video-game-card.component.html',
  styleUrls: ['./video-game-card.component.scss']
})
export class VideoGameCardComponent {
  @Input()
  videojuego: VideojuegoDTO = {
    nombre: '',
    fechaCreacion: new Date(),
    imagenPortada: null,
    sinopsis: '',
    tags: []
  };

  desplegado: boolean = false

  sanitizeImageBlob(){
    return this.sanitizer.bypassSecurityTrustUrl("data:image/png;base64," + this.videojuego.imagenPortada);
  }

  //Solo mostramos las primeras x palabras
  sinopsisCorta(palabras: number){
    return this.videojuego.sinopsis.split(' ').slice(0, palabras).join(' ') + '...';
  }

  //Solo mostramos las primeras x palabras
  nombreSinEspacios(){
    return this.videojuego.nombre.replaceAll(' ', '_');
  }

  constructor(private sanitizer: DomSanitizer){ }
}
