function habilitarBoton(input) {
    const boton = input.parentElement.querySelector(".btn-guardar");
    boton.disabled = input.value.trim() === "";
}

