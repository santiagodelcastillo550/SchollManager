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

// =============== MODAL ELIMINAR =====================
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


document.addEventListener("DOMContentLoaded", () => {
    // elementos modal editar
    const modalEditar = document.getElementById("modalEditar");
    const cancelarEditar = document.getElementById("cancelarEditar");
    const formEditar = document.getElementById("formEditar");

    const inputEditId = document.getElementById("editId");
    const inputEditNombre = document.getElementById("editNombre");
    const inputEditEmail = document.getElementById("editEmail");
    const inputEditRol = document.getElementById("editRol");

    if (!modalEditar || !cancelarEditar || !formEditar) {
        console.error("Elementos de modal editar no encontrados");
        return;
    }

    // Seleccionamos botones editar (todos)
    const botonesEditar = document.querySelectorAll(".edit-btn");

    botonesEditar.forEach(btn => {
        btn.addEventListener("click", (e) => {
            e.preventDefault();

            // Obtenemos los datos desde los atributos data-*
            const id = btn.dataset.id;
            const nombre = btn.dataset.nombre || '';
            const email = btn.dataset.email || '';
            const rol = btn.dataset.rol || '';

            // Rellenamos inputs
            inputEditId.value = id;
            inputEditNombre.value = nombre;
            inputEditEmail.value = email;
            inputEditRol.value = rol;

            // Ponemos la acción del form (usa la misma convención que en eliminar)
            // Si tu controlador espera POST a /admin/editar-usuario/{id}:
            formEditar.action = "/admin/editar-usuario/" + id;

            // Abrimos modal usando clase .show (igual que eliminar)
            modalEditar.classList.add("show");
            modalEditar.setAttribute('aria-hidden', 'false');
            console.log("Modal editar abierta para id:", id);
        });
    });

    // Cerrar modal editar
    cancelarEditar.addEventListener("click", () => {
        modalEditar.classList.remove("show");
        modalEditar.setAttribute('aria-hidden', 'true');
    });

    // Cerrar con clic fuera
    window.addEventListener("click", (e) => {
        if (e.target === modalEditar) {
            modalEditar.classList.remove("show");
            modalEditar.setAttribute('aria-hidden', 'true');
        }
    });

    // Cerrar con ESC
    window.addEventListener("keydown", (e) => {
        if (e.key === "Escape") {
            modalEditar.classList.remove("show");
            modalEditar.setAttribute('aria-hidden', 'true');
        }
    });
});



function mostrarToast(mensaje, tipo) {
    const toast = document.createElement('div');
    toast.className = `toast toast-${tipo}`;
    toast.textContent = mensaje;

    document.getElementById('toastContainer').appendChild(toast);

    setTimeout(() => toast.remove(), 4000);
}


