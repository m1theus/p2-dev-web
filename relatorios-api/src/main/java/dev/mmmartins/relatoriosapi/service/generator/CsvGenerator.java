package dev.mmmartins.relatoriosapi.service.generator;

import com.opencsv.CSVWriter;
import dev.mmmartins.relatoriosapi.controller.exception.BusinessException;
import dev.mmmartins.relatoriosapi.model.CsvEntity;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;


@Component
public class CsvGenerator<T> implements ArquivoGenerator<T> {
    @Override
    public byte[] generate(final List<String> headers, final List<T> data) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(baos))) {
            writer.writeNext(headers.toArray(new String[0]));

            data.forEach(item -> writer.writeNext(((CsvEntity) item).getRecord()));

        } catch (final Exception e) {
            throw new BusinessException("Erro ao gerar arquivo CSV.");
        }
        return baos.toByteArray();
    }
}
