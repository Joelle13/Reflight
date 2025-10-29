export interface User {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
}

export type UserCreateInput = Omit<User, 'userId'>;
