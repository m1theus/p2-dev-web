package dev.mmmartins.relatoriosapi.service.generator;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import dev.mmmartins.relatoriosapi.controller.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class PdfGenerator<T> implements ArquivoGenerator<T> {

    @Override
    public byte[] generate(final List<String> headers, final List<T> data) {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            document.add(new Paragraph(String.format("Relatório %ss", data.getFirst().getClass().getSimpleName())));

            Table table = new Table(headers.size());

            for (String header : headers) {
                table.addHeaderCell(new Cell().add(new Paragraph(header)));
            }

            for (T entity : data) {
                Field[] fields = entity.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object value = field.get(entity);
                    table.addCell(new Cell().add(new Paragraph(value != null ? value.toString() : "")));
                }
            }

            document.add(table);
        } catch (final Exception e) {
            throw new BusinessException("Erro ao gerar arquivo PDF.");
        }

        return baos.toByteArray();
    }

}
