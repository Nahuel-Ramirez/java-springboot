// Call the dataTables jQuery plugin
$(document).ready(function () {
  revisarToken();
  cargarUsuarios();
  $("#usuarios").DataTable();
  actualizarUsuario();
});

function cerrarSesion() {
  localStorage.setItem("token", null);
  window.location.href = "login.html";
}

function actualizarUsuario() {
  document.getElementById("email-usuario").innerHTML =
    localStorage.getItem("nombre");
}

function revisarToken() {
  let token = localStorage.getItem("token");
  if (!token) {
    window.location.href = "login.html";
  }
}

async function cargarUsuarios() {
  const request = await fetch("api/usuarios", {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: localStorage.getItem("token"),
    },
  });
  const usuarios = await request.json();
  console.log(usuarios);

  try {
    let listadoHtml = "";
    for (let usuario of usuarios) {
      let telefono = usuario.telefono == null ? "-" : usuario.telefono;

      const botonEliminar =
        '<a href="#" onclick="eliminarUsuario(' +
        usuario.id +
        ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
      let usuarioHtml =
        "<tr><td>" +
        usuario.id +
        "</td><td>" +
        usuario.nombre +
        " " +
        usuario.apellido +
        "</td><td>" +
        usuario.email +
        "</td><td>" +
        telefono +
        "</td><td>" +
        botonEliminar +
        "</td></tr>";

      listadoHtml += usuarioHtml;
    }
    document.querySelector("#usuarios tbody").innerHTML = listadoHtml;
  } catch (error) {
    console.error(error);
    window.location.href = "login.html";
  }
}

async function eliminarUsuario(id) {
  if (!confirm("¿Desea eliminar el usuario?")) {
    return;
  }

  const request = await fetch("api/usuarios/" + id, {
    method: "DELETE",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: localStorage.getItem("token"),
    },
  });

  location.reload();
}
