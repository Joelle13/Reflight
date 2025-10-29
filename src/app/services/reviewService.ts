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

}
