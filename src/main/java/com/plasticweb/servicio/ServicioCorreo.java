package com.plasticweb.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServicioCorreo {

    @Autowired
    private JavaMailSender mailSender;
    private Random random = new Random();

    // Genera código de verificación de 6 dígitos
    public String generarCodigoVerificacion() {
        return String.format("%06d", random.nextInt(1000000));
    }

    // Envía el código al correo
    public void enviarCodigo(String destinatario, String codigo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Código de Verificación - Platic Web");
        mensaje.setText("Tu código de verificación es: " + codigo);

        mailSender.send(mensaje);
    }
}
