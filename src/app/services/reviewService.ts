import {Injectable} from "@angular/core";
import BaseService from "./baseService";
import {environment} from "../environnement/environnement";
import {Review, ReviewCreateInput} from "../data/review";

@Injectable()
export class ReviewService extends BaseService<Review, ReviewCreateInput>{
  private reviewsUrl = `${environment.apiUrl}v1/reviews`;

  getEndpointUrl(): string {
    return "v1/reviews";
  }

  getCountByFlightId(flightId: string) {
    return this.http.get<number>(`${this.reviewsUrl}/count/flight/${flightId}`);
  }

  searchReviews(params: any) {
    return this.http.get<Review[]>(`${this.reviewsUrl}/search`, { params });

  }

  sortReviews(sortValue: string, desc: boolean) {
    return this.http.get<Review[]>(`${this.reviewsUrl}/sorted`, { params: { sortBy: sortValue, desc: desc } });
  }
}
