import {Injectable} from "@angular/core";
import BaseService from "./baseService";
import {User, UserCreateInput} from "../data/user";
import {environment} from "../environnement/environnement";

@Injectable()
export class UserService extends BaseService<User, UserCreateInput>{
  private usersUrl = `${environment.apiUrl}v1/users`;

  getEndpointUrl(): string {
    return "v1/users";
  }

  findByEmail(email: string) {
    return this.http.get<User>(`${this.usersUrl}/email/${email}`);
  }
}
