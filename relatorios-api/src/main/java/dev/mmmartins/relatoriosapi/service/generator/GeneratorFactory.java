package dev.mmmartins.relatoriosapi.service.generator;


import dev.mmmartins.relatoriosapi.controller.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class GeneratorFactory {
    public static <T> ArquivoGenerator<T> getGenerator(final String generatorName, final Class<T> type) {
        return switch (generatorName) {
            case "pdf" -> new PdfGenerator<>();
            case "xlsx" -> new XlsxGenerator<>();
            case "csv" -> new CsvGenerator<>();
            default -> throw new BusinessException(HttpStatus.BAD_REQUEST, "Generator " + generatorName + " not supported");
        };
    }
}
