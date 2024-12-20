import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { VideojuegoDTO } from 'src/app/entities/videojuego-dto';

@Injectable({
  providedIn: 'root'
})
export class VideoGameService {

  url:string = "http://localhost:8080/api/v1/videojuego";

  constructor(private http:HttpClient) { }

  getVideojuegoByName(nombre: string):Observable<VideojuegoDTO>{
    return this.http.get<VideojuegoDTO>(this.url + '/' + nombre);
  }

  getVideojuegos():Observable<VideojuegoDTO[]>{
    return this.http.get<VideojuegoDTO[]>(this.url);
  }

  getVideojuegosFiltradoParcial(busqueda: string):Observable<VideojuegoDTO[]>{
    let params = new HttpParams().set('nombre', busqueda);

    return this.http.get<VideojuegoDTO[]>(this.url, { params });
  }

  postVideojuego(videojuego:VideojuegoDTO, token:string){
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });

    const formData = new FormData();
    formData.append('nombre', videojuego.nombre);
    formData.append('fechaCreacion', videojuego.fechaCreacion.getTime().toString());
    formData.append('sinopsis', videojuego.sinopsis);

    //TODO - Que funcionen los tags
    videojuego.tags.forEach(tag => formData.append('tags[]', tag));

    if (videojuego.imagenPortada) {
      formData.append('imagenPortada', videojuego.imagenPortada);
      console.log(formData.get('imagenPortada'))
    }

    return this.http.post(this.url, formData, {headers: headers});
  }
}
