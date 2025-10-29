export interface User {
  userId: string;
  firstName: string;
  lastName: string;
  email: string;
}

export type UserCreateInput = Omit<User, 'userId'>;
