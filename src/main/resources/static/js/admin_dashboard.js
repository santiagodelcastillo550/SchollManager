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

document.addEventListener("DOMContentLoaded", () => {
    const modal = document.getElementById("modalEliminar");
    const cancelar = document.getElementById("cancelarEliminar");
    const formEliminar = document.getElementById("formEliminar");
    const toastContainer = document.getElementById("toastContainer");

    if (!modal || !cancelar || !formEliminar || !toastContainer) {
        console.error("No se encontraron los elementos clave");
        return;
    }

    // Seleccionamos todos los botones de eliminar
    const botonesEliminar = document.querySelectorAll(".delete-btn");

    botonesEliminar.forEach(btn => {
        btn.addEventListener("click", (event) => {
            event.preventDefault();
            const id = btn.dataset.id;
            formEliminar.action = "/admin/eliminar-usuario/" + id;
            modal.classList.add("show");
            console.log("Modal abierta para id:", id);
        });
    });

    // Cerrar modal
    cancelar.addEventListener("click", () => modal.classList.remove("show"));
    window.addEventListener("click", (e) => { if (e.target === modal) modal.classList.remove("show"); });
    window.addEventListener("keydown", (e) => { if (e.key === "Escape") modal.classList.remove("show"); });

    // Mostrar toast de éxito si hay mensaje de Thymeleaf
    const mensaje = /*[[${mensaje}]]*/ ''; // Thymeleaf reemplaza si hay flash attribute
    if (mensaje) {
        const toast = document.createElement("div");
        toast.classList.add("toast", "toast-success");
        toast.textContent = mensaje;
        toastContainer.appendChild(toast);
        setTimeout(() => toast.remove(), 4000);
    }
});




function mostrarToast(mensaje, tipo) {
    const toast = document.createElement('div');
    toast.className = `toast toast-${tipo}`;
    toast.textContent = mensaje;

    document.getElementById('toastContainer').appendChild(toast);

    setTimeout(() => toast.remove(), 4000);
}


