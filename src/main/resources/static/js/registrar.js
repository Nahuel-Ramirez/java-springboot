$(document).ready(function () {
  //on ready
});

async function registrarUsuario() {
  let datos = {};
  datos.nombre = document.getElementById("idNombre").value;
  datos.apellido = document.getElementById("idApellido").value;
  datos.email = document.getElementById("idEmail").value;
  datos.telefono = document.getElementById("idTelefono").value;

  datos.password = document.getElementById("idPassword").value;
  let repetirPassword = (datos.passwordRepetida =
    document.getElementById("idPasswordRepetida").value);

  if (repetirPassword != datos.password) {
    alert("La contraseña no coincide");
    return;
  }

  const request = await fetch("api/usuarios", {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos),
  });
  if (request.status == 200) {
    localStorage.setItem("nombre", datos.nombre);
    alert("¡Excelente! Ahora logueate para continuar");
    window.location.href = "login.html";
  }
}
