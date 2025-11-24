// Filtro de usuarios por texto
function filtrarUsuarios() {
    const input = document.getElementById("buscarUsuarios");
    const filtro = input.value.toLowerCase();
    const tabla = document.getElementById("tablaUsuarios");
    const filas = tabla.getElementsByTagName("tr");

    for (let i = 1; i < filas.length; i++) {
        let fila = filas[i];
        let textoFila = fila.textContent.toLowerCase();
        fila.style.display = textoFila.includes(filtro) ? "" : "none";
    }
}

let modal = document.getElementById("modalEliminar");
let confirmarEliminar = document.getElementById("confirmarEliminar");
let cancelarEliminar = document.getElementById("cancelarEliminar");

// Abrir modal con ID dinámico
function abrirModalEliminar(id) {
    confirmarEliminar.href = "/admin/eliminar-usuario/" + id;
    modal.style.display = "block";
}

// Cerrar modal
cancelarEliminar.onclick = () => modal.style.display = "none";
window.onclick = (e) => { if (e.target === modal) modal.style.display = "none"; }

