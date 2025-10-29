import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function dateNotAfterTodayValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const currentDate = new Date().setHours(0, 0, 0, 0);
    const selectedDate = new Date(control.value).setHours(0, 0, 0, 0);
    return selectedDate > currentDate ? { dateNotAfterToday: true } : null;
  };
}
