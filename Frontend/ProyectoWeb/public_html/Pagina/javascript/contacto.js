document.getElementById('contactForm').addEventListener('submit', function(event) {
  event.preventDefault(); 

  alert('¡Gracias por contactarnos! Pronto nos comunicaremos contigo.');
  this.reset();
});

