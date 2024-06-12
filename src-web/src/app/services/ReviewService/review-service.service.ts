import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateReviewData } from 'src/app/entities/createReviewData';
import { Review } from 'src/app/entities/review';
import { ReviewCompleta } from 'src/app/entities/reviewCompleta';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  url:string = "http://localhost:8080/api/v1/review";

  constructor(private http:HttpClient) { }

  getReviews():Observable<ReviewCompleta[]>{
    return this.http.get<ReviewCompleta[]>(this.url);
  }

  getReviewsByVideojuego(nombreVideojuego: string):Observable<Review[]>{
    return this.http.get<Review[]>(this.url + '/' + nombreVideojuego);
  }

  postVideojuego(review:CreateReviewData, token:string){
    let headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });

    return this.http.post(this.url, review, {headers: headers});
  }
}
