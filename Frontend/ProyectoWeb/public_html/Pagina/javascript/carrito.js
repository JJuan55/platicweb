document.addEventListener("DOMContentLoaded", () => {
  const contenedor = document.getElementById("items-carrito");
  const resumen = document.getElementById("resumen-detalle");

  let carrito = JSON.parse(localStorage.getItem("carrito")) || [];

  function actualizarCarrito() {
    contenedor.innerHTML = "";
    resumen.innerHTML = "";

    if (carrito.length === 0) {
      contenedor.innerHTML = "<p>Tu carrito está vacío.</p>";
      return;
    }

    let total = 0;

    carrito.forEach((item, index) => {
      const div = document.createElement("div");
      div.classList.add("item-carrito");
      div.innerHTML = `
        <img src="${item.imagen}" alt="${item.nombre}" />
        <div class="item-info">
          <h4>${item.nombre}</h4>
          <p>Cantidad: <span class="cantidad">${item.cantidad}</span></p>
        </div>
        <div class="item-precio">$${(item.precio * item.cantidad).toLocaleString()} COP</div>
        <button class="eliminar-btn" data-index="${index}">Eliminar</button>
      `;
      contenedor.appendChild(div);
      total += item.precio * item.cantidad;
    });

    resumen.innerHTML = `
      <div class="total">Total: $${total.toLocaleString()} COP</div>
      <button class="checkout-btn" id="finalizarCompraBtn">Finalizar Compra</button>
    `;

    document.querySelectorAll(".eliminar-btn").forEach(btn => {
      btn.addEventListener("click", eliminarItem);
    });

    document.getElementById("finalizarCompraBtn").addEventListener("click", finalizarCompra);
  }

  function eliminarItem(event) {
    const index = event.target.getAttribute("data-index");
    carrito.splice(index, 1);
    localStorage.setItem("carrito", JSON.stringify(carrito));
    actualizarCarrito();
  }

  function finalizarCompra() {
    alert("Compra finalizada. ¡Gracias por tu compra!");
    carrito = []; // Limpiar el carrito
    localStorage.setItem("carrito", JSON.stringify(carrito));
    actualizarCarrito();
  }

  actualizarCarrito();
});


