import {Flight} from "./flight";
import {User} from "./user";

export interface Review {
  id: string;
  flight:Flight;
  rating: number;
  comments: string;
  user: User;
  reviewDate :Date;
  status:string;
  response?: string;
}

export type ReviewCreateInput = Omit<Review, 'reviewId' | 'user' | 'flight'>&{
  flightId: string;
  userId: string;
}

