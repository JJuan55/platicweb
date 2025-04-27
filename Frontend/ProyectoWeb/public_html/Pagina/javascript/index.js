const productos = [
    {
        nombre: "Bolso reciclado",
        tipo: "bolsos",
        precio: 18000,
        disponible: true,
        descripcion: "Bolso hecho a mano con materiales reciclados.",
        vendedor: "María López",
        imagen: "https://i.pinimg.com/222x/82/c4/3f/82c43f3ad354a70f2241eb83a8d7b750.jpg"
    },
    {
        nombre: "Maceta ecológica",
        tipo: "macetas",
        precio: 12000,
        disponible: true,
        descripcion: "Maceta ecológica ideal para tu jardín.",
        vendedor: "Carlos Ruiz",
        imagen: "https://i.pinimg.com/236x/24/3e/bd/243ebdb3a754407943a2d1db8b4575ab.jpg"
    },
    {
        nombre: "Lámpara con botellas",
        tipo: "decoración",
        precio: 35000,
        disponible: false,
        descripcion: "Lámpara elaborada con botellas recicladas.",
        vendedor: "Luz Verde",
        imagen: "https://i.pinimg.com/474x/c8/c6/59/c8c65949d7d1bf33192937dbbdc3d258.jpg"
    },
    {
        nombre: "Florero reciclado",
        tipo: "decoración",
        precio: 16000,
        disponible: true,
        descripcion: "Florero moderno hecho de materiales reutilizados.",
        vendedor: "Juan Moreno",
        imagen: "https://i.pinimg.com/236x/d4/06/a1/d406a1d91e479f615fa9d8dce013c2fc.jpg"
    }
];

const contenedor = document.getElementById("product-carousel");
const filtroForm = document.querySelector(".filtros form");
const slider = document.getElementById("myRange");
const output = document.getElementById("demo");

// Función para renderizar productos en el contenedor
function renderizarProductos(lista) {
    contenedor.innerHTML = ""; // Limpiar el contenedor

    if (lista.length === 0) {
        contenedor.innerHTML = "<p>No se encontraron productos.</p>";
        return;
    }

    lista.forEach(prod => {
        const card = document.createElement("div");
        card.classList.add("producto");
        card.innerHTML = `
            <div class="imagen-con-overlay">
                <img src="${prod.imagen}" alt="${prod.nombre}" />
                <div class="overlay">
                    <h4>${prod.nombre}</h4>
                    <p>$${prod.precio.toLocaleString()} COP</p>
                </div>
            </div>
        `;
        card.addEventListener("click", () => {
            localStorage.setItem("productoSeleccionado", JSON.stringify(prod));
            window.location.href = "producto.html";
        });
        contenedor.appendChild(card);
    });
}

// Función para filtrar productos según los criterios seleccionados
function filtrarProductos() {
    const tipo = filtroForm.tipo.value;
    const precioMax = parseInt(slider.value);  // Usar el valor del slider para el precio máximo
    const soloDisponibles = filtroForm.disponible.checked;

    const filtrados = productos.filter(p => {
        const tipoValido = tipo === "" || p.tipo === tipo;
        const precioValido = p.precio <= precioMax;
        const disponibilidadValida = !soloDisponibles || p.disponible;
        return tipoValido && precioValido && disponibilidadValida;
    });

    renderizarProductos(filtrados);
}

// Evento para manejar el envío del formulario de filtros
filtroForm.addEventListener("submit", (e) => {
    e.preventDefault();
    filtrarProductos(); 
});

// Función para actualizar el valor del slider y mostrarlo en el elemento output
slider.oninput = function() {
    output.innerHTML = `$${parseInt(this.value).toLocaleString()}`; 
};

// Mostrar el valor inicial del slider
output.innerHTML = `$${parseInt(slider.value).toLocaleString()}`;

// Evento para inicializar la página
document.addEventListener("DOMContentLoaded", () => {
    renderizarProductos(productos); 
    manejarSesion(); 
    manejarMenuPerfil(); 
});
