// Call the dataTables jQuery plugin
$(document).ready(function () {
  cargarUsuarios();
  $("#usuarios").DataTable();
  actualizarUsuario();
});

function actualizarUsuario() {
  document.getElementById("email-usuario").innerHTML =
    localStorage.getItem("nombre");
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
