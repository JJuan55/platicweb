document.addEventListener("DOMContentLoaded", () => {
  const producto = JSON.parse(localStorage.getItem("productoSeleccionado"));

  if (!producto) {
    document.querySelector(".detalle-producto").innerHTML = "<p>Producto no encontrado.</p>";
    return;
  }

  document.querySelector(".imagenes-producto img").src = producto.imagen;
  document.querySelector(".imagenes-producto img").alt = producto.nombre;
  document.querySelector(".detalles h2").textContent = producto.nombre;
  document.querySelector(".precio").textContent = `$${producto.precio.toLocaleString()} COP`;
  document.querySelector(".descripcion").textContent = producto.descripcion || "Este producto no tiene descripción.";
  document.querySelector(".vendedor").innerHTML = `<strong>Vendido por:</strong> ${producto.vendedor || "Vendedor anónimo"}`;

  const btnAgregar = document.querySelector(".btn-agregar");
  if (!producto.disponible) {
    btnAgregar.disabled = true;
    btnAgregar.textContent = "Producto no disponible";
    btnAgregar.style.backgroundColor = "#ccc";
    btnAgregar.style.cursor = "not-allowed";
  }

  btnAgregar.addEventListener("click", () => {
    const cantidad = parseInt(document.querySelector("#cantidad").value);
    if (cantidad > 0) {
      alert(`Se agregaron ${cantidad} unidades de "${producto.nombre}" al carrito.`);
      // Aquí podrías guardar los datos en localStorage, o enviarlos a un backend
    }
  });
});



