import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, map, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url:string = "http://localhost:8080/api/v1/auth/login";
  body:any = {
    "email": "admin@admin.com",
    "password": "passwordadmin"
  };
  headers:HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });
  token:string = "";

  constructor(private http:HttpClient) { }

  getToken():Observable<string>{
    let storageToken = localStorage.getItem("token");
    if(storageToken == null){
      return this.peticionToken();
    }
    else{
      return of(storageToken)
    }
  }

  peticionToken() {
    return this.http.post<any>(this.url, this.body, {headers: this.headers})
    .pipe(
      map(resp => {
        let token = resp.token;
        localStorage.setItem("token", token);
        return token;
      })
    );
  }
}
