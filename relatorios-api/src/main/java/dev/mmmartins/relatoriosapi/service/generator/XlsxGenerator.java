package dev.mmmartins.relatoriosapi.service.generator;

import dev.mmmartins.relatoriosapi.controller.exception.BusinessException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class XlsxGenerator<T> implements ArquivoGenerator<T> {
    @Override
    public byte[] generate(final List<String> headers, final List<T> data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                headerRow.createCell(i).setCellValue(headers.get(i));
            }

            for (int i = 0; i < data.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                T entity = data.get(i);
                Field[] fields = entity.getClass().getDeclaredFields();

                for (int j = 0; j < fields.length; j++) {
                    fields[j].setAccessible(true);
                    Object value = fields[j].get(entity);
                    dataRow.createCell(j).setCellValue(value != null ? value.toString() : "");
                }
            }

            workbook.write(baos);
        } catch (final Exception e) {
            throw new BusinessException("Erro ao gerar arquivo XLSX.");
        }
        return baos.toByteArray();
    }
}
