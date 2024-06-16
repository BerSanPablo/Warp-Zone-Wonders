import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { CreateAccountData } from 'src/app/entities/createAccountData';
import { UserService } from 'src/app/services/UserService/user-service.service';

@Component({
  selector: 'app-editar-perfil',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './editar-perfil.component.html',
  styleUrls: ['./editar-perfil.component.scss']
})
export class EditarPerfilComponent {
  //Datos para el formulario
  createAccountData: CreateAccountData = {
    nombre: '',
    mail: '',
    comunidad: 'Casual',
    password: '',
    imagen: null
  }
  passwordConfirm: string = ''
  errorNombre: string | null = null
  errorEmail: string | null = null
  errorPassword: string | null = null
  loading: boolean = false

  //Datos para la conexion
  url = "http://localhost:8080/api/v1";

  constructor(protected usuarioService: UserService, private http: HttpClient, private router: Router) {
    if(usuarioService.getUser() == null){
      this.router.navigate(['/']);
    }
  }

  async submit() {
    this.loading = true



    try{
      if(await this.usuarioService.updateUser(this.createAccountData.comunidad, this.createAccountData.imagen)){
        this.router.navigate(['/perfil'])
      }

    }finally{
      this.loading = false
    }
  }

  async validacionCompleta(){

    //Nombre
    if(this.createAccountData.nombre.trim() === ''){
      this.errorNombre = 'No se permite dejar el nombre vacío'
    }
    else{
      const responseNombre = await firstValueFrom(this.http.post<any>(this.url + '/auth/checkNombre', this.createAccountData.nombre));
      if(responseNombre){
        this.errorNombre = null
      }
      else{
        this.errorNombre = 'El nombre ya está en uso'
      }
    }

    //Email
    if(this.createAccountData.mail.trim() === ''){
      this.errorEmail = 'No se permite dejar el email vacío'
    }
    else{

      if(!/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(this.createAccountData.mail)){
        this.errorEmail = 'El email no tiene un formato correcto'
      }
      else{
        const responseNail = await firstValueFrom(this.http.post<any>(this.url + '/auth/checkMail', this.createAccountData.mail));
        if(responseNail){
          this.errorEmail = null
        }
        else{
          this.errorEmail = 'El email ya está en uso'
        }
      }
    }

    //Password
    if(this.createAccountData.password.trim() === '' || this.passwordConfirm.trim() === ''){
      this.errorPassword = 'Hay que rellenar y confirmar la contraseña'
    }
    else{
      if(this.createAccountData.password != this.passwordConfirm){
        this.errorPassword = 'Las contraseñas no coinciden'
      }
      else{
        this.errorPassword = null
      }
    }

    return this.errorNombre == null && this.errorEmail == null && this.errorPassword == null
  }

  actualizarImagen(event: any){
    try{
      if (event.target && event.target.files[0]){
        this.createAccountData.imagen = event.target.files[0];
      }
    }catch(err){

    }
  }
}
