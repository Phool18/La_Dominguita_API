package la_dominga.servidor;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.barcodes.Barcode128;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import la_dominga.entidades.DatosCompra;
import la_dominga.entidades.dto.ImprimirPedidosDTO;

@Service
public class BoletaService {

    public byte[] generarBoletaPdf(ImprimirPedidosDTO datosPedido) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A6); // Cambio a un tamaño de página más ajustado

        // Logo
        Image logo = new Image(ImageDataFactory.create(new ClassPathResource("logo.png").getURL()));
        logo.scaleToFit(80, 80); // Reducción del tamaño del logo
        logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
        logo.setMarginBottom(5); // Reducción del margen

        // Encabezado
        Paragraph header = new Paragraph("Boleta de Compra")
                .setFontSize(14) // Reducción del tamaño de la fuente
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(5); // Reducción del margen

        // Información de la compra
        Paragraph compraInfo = new Paragraph()
                .add(new Text("Fecha: ").setBold())
                .add(datosPedido.getCarritoDeCompras().getFechaCompra().toString())
                .setFontSize(10) // Reducción del tamaño de la fuente
                .setMarginBottom(5); // Reducción del margen
        Paragraph userinfo = new Paragraph()
                .add(new Text("Fecha: ").setBold())
                .add(datosPedido.getCarritoDeCompras().getCliente().getNombreCompleto().toString())
                .setFontSize(10) // Reducción del tamaño de la fuente
                .setMarginBottom(5); // Reducción del margen

        // Tabla para detalles de compra
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 1, 2}))
                .useAllAvailableWidth()
                .setMarginBottom(5); // Reducción del margen inferior

        // Encabezados de la tabla
        table.addHeaderCell(new Cell().add(new Paragraph("Producto").setBold().setFontSize(10)));
        table.addHeaderCell(new Cell().add(new Paragraph("Cantidad").setBold().setFontSize(10)));
        table.addHeaderCell(new Cell().add(new Paragraph("Precio").setBold().setFontSize(10)));

        // Añadir detalles a la tabla
        for (DatosCompra detalle : datosPedido.getDetallePedido()) {
            table.addCell(new Cell().add(new Paragraph(detalle.getProducto().getNombre()).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(detalle.getCantidad())).setFontSize(10)));
            table.addCell(new Cell().add(new Paragraph("S/. " + detalle.getPrecio()).setFontSize(10)));
        }

        // Total

        double montoTotal = datosPedido.getCarritoDeCompras().getMonto();
        double montoIgv = montoTotal * 0.18; // Calcula el 18% del monto total
        double precioSinIgv = montoTotal - montoIgv; // Precio total menos IGV

        Paragraph precioSinIgvParagraph = new Paragraph("Sub total: " + String.format("%.2f", precioSinIgv))
                .setFontSize(10) // Reducción del tamaño de la fuente
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(5);
        Paragraph igv = new Paragraph("IGV: S/. " + String.format("%.2f", montoIgv))
                .setFontSize(10) // Reducción del tamaño de la fuente
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(5);
        Paragraph total = new Paragraph("Total: S/. " + datosPedido.getCarritoDeCompras().getMonto())
                .setFontSize(10) // Reducción del tamaño de la fuente
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginBottom(5); // Reducción del margen inferior

        // Código de barras
        Barcode128 code128 = new Barcode128(pdf);
        code128.setCode(Integer.toString(datosPedido.getCarritoDeCompras().getId()));
        code128.setCodeType(Barcode128.CODE128);
        Image code128Image = new Image(code128.createFormXObject(null, null, pdf));
        code128Image.scaleToFit(400, 80); // Ajuste del tamaño del código de barras
        code128Image.setHorizontalAlignment(HorizontalAlignment.CENTER);
        code128Image.setMarginTop(5);

        // Añadiendo elementos al documento
        document.add(logo);
        document.add(header);
        document.add(compraInfo);
        document.add(userinfo);
        document.add(table);
        document.add(precioSinIgvParagraph);
        document.add(igv);
        document.add(total);
        document.add(code128Image);

        document.close();
        return baos.toByteArray();
    }
}
