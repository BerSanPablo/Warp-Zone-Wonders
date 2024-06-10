import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VideojuegoDTO } from 'src/app/entities/videojuego-dto';
import { FormsModule } from '@angular/forms';
import { UserService } from 'src/app/services/UserService/user-service.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-create-juego',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-juego.component.html',
  styleUrls: ['./create-juego.component.scss']
})
export class CreateJuegoComponent {

  //Datos para el formulario
  createJuegoData: VideojuegoDTO = {
    nombre: '',
    fechaCreacion: new Date(Date.now()),
    imagenPortada: null,
    sinopsis: '',
    tags: []
  }

  errorNombre: string | null = null
  errorSinopsis: string | null = null
  loading: boolean = false

  constructor(protected usuarioService: UserService, private http: HttpClient, private router: Router) {
    const user = usuarioService.getUser()

    //Redireccionamos si no tienes permisos
    if(user == null || !user.roles.includes('ROLE_ADMIN')){
      this.router.navigate(['/']);
    }}

  async submit() {
    const url: string = "http://localhost:8080/api/v1/videojuego";
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + this.usuarioService.getUser()!.token
    });

    this.loading = true

    try{
      //Comprobamos si hay errores
      const formularioValidado: boolean = await this.validacionCompleta()

      //Si pasamos todas las validaciones intentamos crear el usuario
      if(formularioValidado){
        const formData = new FormData();
        formData.append('nombre', this.createJuegoData.nombre);
        formData.append('fechaCreacion', this.createJuegoData.fechaCreacion.getTime().toString());
        formData.append('sinopsis', this.createJuegoData.sinopsis);

        for (let index = 0; index < this.createJuegoData.tags.length; index++) {
          formData.append(`tags[${index}]`, this.createJuegoData.tags[index])
        }

        if (this.createJuegoData.imagenPortada) {
          formData.append('imagenPortada', this.createJuegoData.imagenPortada);
          console.log(formData.get('imagenPortada'))
        }

        //Esperamos a que se guarde
        await firstValueFrom(this.http.post(url, formData, {headers: headers}));

        //Nos vamos al listado
        this.router.navigate(['/']);
      }

    }finally{
      this.loading = false
    }
  }

  async validacionCompleta(){

    //Nombre
    if(this.createJuegoData.nombre.trim() === ''){
      this.errorNombre = 'No se permite dejar el nombre vacío'
    }
    else{
      this.errorNombre = null
    }

    //Sinopsis
    if(this.createJuegoData.sinopsis.trim() === ''){
      this.errorSinopsis = 'No se permite dejar la sinopsis vacía'
    }
    else{
      this.errorSinopsis = null
    }

    return this.errorNombre == null && this.errorSinopsis == null
  }

  actualizarImagen(event: any){
    try{
       if (event.target && event.target.files[0]){
        this.createJuegoData.imagenPortada = event.target.files[0];
      }
    }catch(err){

    }
  }
}
