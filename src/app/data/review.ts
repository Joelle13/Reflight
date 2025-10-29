import {Flight} from "./flight";
import {User} from "./user";

export interface Review {
  reviewId: string;
  flight:Flight;
  rating: number;
  comments: string;
  user: User;
  reviewDate :Date;
  reviewStatus:string;
}

export type ReviewCreateInput = Omit<Review, 'reviewId' | 'user' | 'flight'>&{
  flightId: string;
  userId: string;
}

