import { Injectable } from '@angular/core';
import { UserData } from 'src/app/entities/userData';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {  firstValueFrom, map } from 'rxjs';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  //Clave para encriptacion
  secretKey = 'claveEncriptadoUsuario1234'

  //Datos del usuario
  private userData: UserData | null = null;

  constructor(private http:HttpClient, private sanitizer: DomSanitizer) {

    //Intentamos recoger el usuario del session
    const userString = sessionStorage.getItem('U2FsdGVkX19esp5By6OH6I4AU3+FG/iAEZF3XgqUZjs=')

    //Si está en local lo guardamos otra vez en el servicio
    if(userString){
      this.setUser(JSON.parse(userString))
    }
  }

  async login(email: string, password: string) {

    //Datos para la conexion
    const url = "http://localhost:8080/api/v1";
    const body = {
      "email": email,
      "password": password
    };
    const headersLogin = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    //Recogemos los datos de forma asíncrona
    let tokenResponse: any, datosUsuarioResponse: any
    try{
      //Lanzamos la petición y recogemos el token
      tokenResponse = await firstValueFrom(this.http.post<any>(url + '/auth/login', body, { headers: headersLogin }));

      //Con el token hacemos una petición para recoger los datos del usuario
      const headersData = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + tokenResponse.token
      });
      datosUsuarioResponse = await firstValueFrom(this.http.get<any>(url + '/user/datosUsuario', { headers: headersData }));

    }catch(err){
      return false
    }

    //Creamos el usuario con los datos de las respuestas
    const user = {
      nick: datosUsuarioResponse.nick,
      email: email,
      comunidad: datosUsuarioResponse.comunidad,
      token: tokenResponse.token,
      imagenPerfil: datosUsuarioResponse.imagenPerfil,
      roles: datosUsuarioResponse.roles
    }

    //Guardamos el usuario en el servicio
    this.setUser(user)

    //Guardamos el usuario en sesion
    sessionStorage.setItem('U2FsdGVkX19esp5By6OH6I4AU3+FG/iAEZF3XgqUZjs=', JSON.stringify(user))

    return true
  }

  async loginCreacionCuenta(email:string, token: string) {

    //Datos para la conexion
    const url = "http://localhost:8080/api/v1";
    const headersLogin = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    //Recogemos los datos de forma asíncrona
    let datosUsuarioResponse: any
    try{

      //Con el token hacemos una petición para recoger los datos del usuario
      const headersData = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + token
      });
      datosUsuarioResponse = await firstValueFrom(this.http.get<any>(url + '/user/datosUsuario', { headers: headersData }));

    }catch(err){
      return false
    }

    //Creamos el usuario con los datos de las respuestas
    const user = {
      nick: datosUsuarioResponse.nick,
      email: email,
      comunidad: datosUsuarioResponse.comunidad,
      token: token,
      imagenPerfil: datosUsuarioResponse.imagenPerfil,
      roles: datosUsuarioResponse.roles
    }

    //Guardamos el usuario en el servicio
    this.setUser(user)

    //Guardamos el usuario en sesion
    sessionStorage.setItem('U2FsdGVkX19esp5By6OH6I4AU3+FG/iAEZF3XgqUZjs=', JSON.stringify(user))

    return true
  }

  setUser(data: UserData): void {
    this.userData = data;
  }

  getUser(): UserData | null {
    return this.userData;
  }

  clearUser(): void {
    this.userData = null;
    sessionStorage.removeItem('U2FsdGVkX19esp5By6OH6I4AU3+FG/iAEZF3XgqUZjs=');
  }

  getSrcImagen(){
    if(!this.userData || !this.userData.imagenPerfil){
      return '../../../assets/defaultUser.jpg'
    }

    return this.sanitizer.bypassSecurityTrustUrl("data:image/png;base64," + this.userData.imagenPerfil);
  }
}
