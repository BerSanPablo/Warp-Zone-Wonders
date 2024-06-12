import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReviewService } from 'src/app/services/ReviewService/review-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/UserService/user-service.service';
import { CreateReviewData } from 'src/app/entities/createReviewData';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-create-review',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-review.component.html',
  styleUrls: ['./create-review.component.scss']
})
export class CreateReviewComponent {

  createReviewData: CreateReviewData = {
    nombreUsuario: '',
    nombreVideojuego: '',
    valoracion: 0,
    textoReview: ''
  }

  errorTexto: string | null = null
  loading: boolean = false

  constructor(private usuarioService: UserService, private servicioReview: ReviewService, private http: HttpClient, private route: ActivatedRoute, private router: Router) {
    const nombreVideojuego = this.route.snapshot.paramMap.get('nombre')
    const user = usuarioService.getUser()

    if(nombreVideojuego == null || user == null){
      this.router.navigate(['/']);
      return
    }

    this.createReviewData.nombreVideojuego = nombreVideojuego
    this.createReviewData.nombreUsuario = user.nick
  }

  getRange(total: number): number[] {
    return Array(total).fill(0).map((x, i) => i);
  }

  cambiarValoracion(valoracion: number){
    this.createReviewData.valoracion = valoracion;
  }

  async submit() {
    const url: string = "http://localhost:8080/api/v1/review";
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + this.usuarioService.getUser()!.token
    });

    this.loading = true

    try{
      //Comprobamos si hay errores
      const formularioValidado: boolean = await this.validarNombre()

      //Si pasamos todas las validaciones intentamos crear la review
      if(formularioValidado){

        //Esperamos a que se guarde
        await firstValueFrom(this.http.post(url, this.createReviewData, {headers: headers}));

        //Nos vamos al detalle del videojuego
        this.router.navigate([`/videojuego/${this.createReviewData.nombreVideojuego}`]);
      }

    }finally{
      this.loading = false
    }
  }

  async validarNombre(){

    if(this.createReviewData.textoReview.trim() === ''){
      this.errorTexto = 'No se permite dejar este campo vacío'
    }
    else{
      this.errorTexto = null
    }

    return this.errorTexto == null
  }
}
