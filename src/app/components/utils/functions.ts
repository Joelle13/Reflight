import Swal from "sweetalert2";

export function displayToast(valid : boolean, message : string){
  const Toast = Swal.mixin({
    toast: true,
    position: "top",
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    didOpen: (toast) => {
      toast.onmouseenter = Swal.stopTimer;
      toast.onmouseleave = Swal.resumeTimer;
    }
  });
  if(valid){
    Toast.fire({
      icon: 'success',
      title: message
    });
  } else{
    Toast.fire({
      icon: 'error',
      title: message
    })
  }
}
