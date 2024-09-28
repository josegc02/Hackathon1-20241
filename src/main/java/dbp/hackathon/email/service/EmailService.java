package dbp.hackathon.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import dbp.hackathon.QRapi.domain.QRapiService;
import dbp.hackathon.Ticket.Ticket;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    QRapiService qrapiService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    QRapiService qrService;

    @Async
    public void sendEmail(Ticket ticket) throws MessagingException {
        String qr = qrService.getQR(ticket.getId()).block();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        Context context = new Context();

        context.setVariable("nombre", ticket.getEstudiante().getName());
        context.setVariable("nombrePelicula", ticket.getFuncion().getNombre());
        context.setVariable("fechaFuncion", ticket.getFechaCompra());
        context.setVariable("cantidadEntradas", ticket.getCantidad());
        context.setVariable("precioTotal", ticket.getFuncion().getPrecio() * ticket.getCantidad());
        context.setVariable("qr", qr);

        String htmlContent = templateEngine.process("emailTemplate", context);

        helper.setTo(ticket.getEstudiante().getEmail());
        helper.setSubject("Confirmación de compra de entradas");
        helper.setText(htmlContent, true);
        mailSender.send(message);
    }
}
