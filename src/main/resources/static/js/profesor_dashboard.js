function habilitarBoton(input) {
    const boton = input.parentElement.querySelector(".btn-guardar");
    boton.disabled = input.value.trim() === "";
}

// Interceptar envío del formulario para mostrar mensaje sin recargar
document.addEventListener("DOMContentLoaded", () => {
    const forms = document.querySelectorAll(".nota-form");

    forms.forEach(form => {
        form.addEventListener("submit", function (event) {
            event.preventDefault(); // Evita recargar la página

            const formData = new FormData(this);
            const boton = this.querySelector(".btn-guardar");
            const input = this.querySelector("input[name='valor']");

            // Deshabilitar temporalmente el botón mientras se guarda
            boton.disabled = true;

            fetch(this.action, {
                method: this.method,
                body: formData
            })
            .then(response => {
                if (response.ok) {
                    mostrarMensaje("✅ Nota guardada correctamente");
                    resaltarInput(input, true);
                } else {
                    mostrarMensaje("❌ Error al guardar la nota", true);
                    resaltarInput(input, false);
                }
            })
            .catch(() => {
                mostrarMensaje("⚠️ Error de conexión", true);
                resaltarInput(input, false);
            })
            .finally(() => {
                // Rehabilitar el botón después de 1s
                setTimeout(() => boton.disabled = false, 1000);
            });
        });
    });
});

// --- Función de mensaje flotante ---
function mostrarMensaje(texto, error = false) {
    let mensaje = document.createElement("div");
    mensaje.textContent = texto;
    mensaje.style.position = "fixed";
    mensaje.style.bottom = "20px";
    mensaje.style.right = "20px";
    mensaje.style.padding = "10px 15px";
    mensaje.style.borderRadius = "8px";
    mensaje.style.backgroundColor = error ? "#ff4d4d" : "#4CAF50";
    mensaje.style.color = "white";
    mensaje.style.fontWeight = "bold";
    mensaje.style.boxShadow = "0 0 8px rgba(0,0,0,0.3)";
    mensaje.style.zIndex = "1000";
    mensaje.style.transition = "opacity 0.3s";
    document.body.appendChild(mensaje);

    setTimeout(() => {
        mensaje.style.opacity = "0";
        setTimeout(() => mensaje.remove(), 300);
    }, 2000);
}

// --- Feedback visual en el input ---
function resaltarInput(input, exito = true) {
    const color = exito ? "#c8f7c5" : "#f7c5c5";
    const borde = exito ? "2px solid #4CAF50" : "2px solid #ff4d4d";
    input.style.backgroundColor = color;
    input.style.border = borde;

    setTimeout(() => {
        input.style.backgroundColor = "";
        input.style.border = "";
    }, 1500);
}

document.querySelectorAll('.nota-form').forEach(form => {
    form.addEventListener('submit', function (event) {
        event.preventDefault(); // Evita el envío tradicional
        const formData = new FormData(this);

        fetch(this.action, {
            method: this.method,
            body: formData
        })
        .then(response => {
            if (response.ok) {
                // Refresca la página tras guardar correctamente
                location.reload();
            } else {
                alert("Error al actualizar la nota");
            }
        })
        .catch(err => {
            console.error("Error:", err);
            alert("Error al conectar con el servidor");
        });
    });
});
