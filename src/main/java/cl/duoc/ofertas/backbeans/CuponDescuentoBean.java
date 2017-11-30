/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.ofertas.backbeans;

import cl.duoc.ofertas.entities.Punto;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BarcodeEAN;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Colipavo
 */
@ManagedBean(name = "cuponDescuentoBean")
@ViewScoped
public class CuponDescuentoBean implements Serializable {

    private final static Logger logger = Logger.getLogger(CuponDescuentoBean.class);

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private StreamedContent cupon;

    public CuponDescuentoBean() {

    }

    public void generarCuponDescuento() {
        try {
            if (loginBean.getUsuarioSesionado().getPuntoList() != null && !loginBean.getUsuarioSesionado().getPuntoList().isEmpty()) {
                long puntos = 0;

                for (Punto p : loginBean.getUsuarioSesionado().getPuntoList()) {
                    if (p.getIscobrado().intValue() == 0) {
                        puntos += p.getCantidad().longValue();
                    }
                }

                if (puntos >= 0 && puntos <= 100) {
                    cupon = null;
                    Document document = new Document(PageSize.LETTER);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PdfWriter writer;
                    writer = PdfWriter.getInstance(document, baos);
                    document.addTitle("Cupón de Descuento");
                    document.open();
                    document.add(new Paragraph("Cupón de Descuento"));
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Estimado: " + loginBean.getUsuarioSesionado().getConsumidor().getNombre() + " " + loginBean.getUsuarioSesionado().getConsumidor().getApellidopaterno() + " este cupón es válido por un 5% de descuento en Alimentos."));
                    document.add(new Paragraph("El tope de compra para poder utilizar este cupón es de $100000"));
                    document.add(new Paragraph("Rut Beneficiario: " + loginBean.getUsuarioSesionado().getConsumidor().getRut() + "-" + loginBean.getUsuarioSesionado().getConsumidor().getDv()));
                    document.add(new Paragraph(" "));
                    long randomNumber = System.currentTimeMillis();
                    BarcodeEAN codeEAN = new BarcodeEAN();
                    codeEAN.setCode(String.valueOf(randomNumber));
                    codeEAN.setFont(null);
                    PdfContentByte pdfContentByte = writer.getDirectContent();
                    Image barcode = codeEAN.createImageWithBarcode(pdfContentByte, null, null);
                    barcode.setAlignment(Element.ALIGN_CENTER);
                    document.add(barcode);
                    document.close();
                    writer.close();
                    InputStream stream = new ByteArrayInputStream(baos.toByteArray());
                    cupon = new DefaultStreamedContent(stream, "application/pdf", "cupon_descuento.pdf");
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('dlgDescargaCupon').show();");
                } else if (puntos >= 101 && puntos <= 500) {
                    cupon = null;
                    Document document = new Document(PageSize.LETTER);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PdfWriter writer;
                    writer = PdfWriter.getInstance(document, baos);
                    document.addTitle("Cupón de Descuento");
                    document.open();
                    document.add(new Paragraph("Cupón de Descuento"));
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Estimado: " + loginBean.getUsuarioSesionado().getConsumidor().getNombre() + " " + loginBean.getUsuarioSesionado().getConsumidor().getApellidopaterno() + " este cupón es válido por un 10% de descuento en Alimentos, Electrónica y Línea Blanca."));
                    document.add(new Paragraph("El tope de compra para poder utilizar este cupón es de $150000"));
                    document.add(new Paragraph("Rut Beneficiario: " + loginBean.getUsuarioSesionado().getConsumidor().getRut() + "-" + loginBean.getUsuarioSesionado().getConsumidor().getDv()));
                    document.add(new Paragraph(" "));
                    long randomNumber = System.currentTimeMillis();
                    BarcodeEAN codeEAN = new BarcodeEAN();
                    codeEAN.setCode(String.valueOf(randomNumber));
                    codeEAN.setFont(null);
                    PdfContentByte pdfContentByte = writer.getDirectContent();
                    Image barcode = codeEAN.createImageWithBarcode(pdfContentByte, null, null);
                    barcode.setAlignment(Element.ALIGN_CENTER);
                    document.add(barcode);
                    document.close();
                    writer.close();
                    InputStream stream = new ByteArrayInputStream(baos.toByteArray());
                    cupon = new DefaultStreamedContent(stream, "application/pdf", "cupon_descuento.pdf");
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('dlgDescargaCupon').show();");
                } else if (puntos >= 501) {
                    cupon = null;
                    Document document = new Document(PageSize.LETTER);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    PdfWriter writer;
                    writer = PdfWriter.getInstance(document, baos);
                    document.addTitle("Cupón de Descuento");
                    document.open();
                    document.add(new Paragraph("Cupón de Descuento"));
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph("Estimado: " + loginBean.getUsuarioSesionado().getConsumidor().getNombre() + " " + loginBean.getUsuarioSesionado().getConsumidor().getApellidopaterno() + " este cupón es válido por un 15% de descuento en Alimentos, Electrónica, Línea Blanca y Ropa."));
                    document.add(new Paragraph("El tope de compra para poder utilizar este cupón es de $300000"));
                    document.add(new Paragraph("Rut Beneficiario: " + loginBean.getUsuarioSesionado().getConsumidor().getRut() + "-" + loginBean.getUsuarioSesionado().getConsumidor().getDv()));
                    document.add(new Paragraph(" "));
                    long randomNumber = System.currentTimeMillis();
                    BarcodeEAN codeEAN = new BarcodeEAN();
                    codeEAN.setCode(String.valueOf(randomNumber));
                    codeEAN.setFont(null);
                    PdfContentByte pdfContentByte = writer.getDirectContent();
                    Image barcode = codeEAN.createImageWithBarcode(pdfContentByte, null, null);
                    barcode.setAlignment(Element.ALIGN_CENTER);
                    document.add(barcode);
                    document.close();
                    writer.close();
                    InputStream stream = new ByteArrayInputStream(baos.toByteArray());
                    cupon = new DefaultStreamedContent(stream, "application/pdf", "cupon_descuento.pdf");
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('dlgDescargaCupon').show();");
                }
            } else {
                cupon = null;
                Document document = new Document(PageSize.LETTER);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfWriter writer;
                writer = PdfWriter.getInstance(document, baos);
                document.addTitle("Cupón de Descuento");
                document.open();
                document.add(new Paragraph("Cupón de Descuento"));
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Estimado: " + loginBean.getUsuarioSesionado().getConsumidor().getNombre() + " " + loginBean.getUsuarioSesionado().getConsumidor().getApellidopaterno() + " este cupón es válido por un 5% de descuento en Alimentos."));
                document.add(new Paragraph("El tope de compra para poder utilizar este cupón es de $100000"));
                document.add(new Paragraph("Rut Beneficiario: " + loginBean.getUsuarioSesionado().getConsumidor().getRut() + "-" + loginBean.getUsuarioSesionado().getConsumidor().getDv()));
                document.add(new Paragraph(" "));
                long randomNumber = System.currentTimeMillis();
                BarcodeEAN codeEAN = new BarcodeEAN();
                codeEAN.setCode(String.valueOf(randomNumber));
                codeEAN.setFont(null);
                PdfContentByte pdfContentByte = writer.getDirectContent();
                Image barcode = codeEAN.createImageWithBarcode(pdfContentByte, null, null);
                barcode.setAlignment(Element.ALIGN_CENTER);
                document.add(barcode);
                document.close();
                writer.close();
                InputStream stream = new ByteArrayInputStream(baos.toByteArray());
                cupon = new DefaultStreamedContent(stream, "application/pdf", "cupon_descuento.pdf");
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgDescargaCupon').show();");
            }

        } catch (Exception e) {
            logger.error("Error grave procesando Login.", e);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Se ha encontrado un error grave al procesar Login.", "Error grave procesando Login.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("growl", message);
        }
    }

    public StreamedContent getCupon() {
        return cupon;
    }

    public void setCupon(StreamedContent cupon) {
        this.cupon = cupon;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }
}
