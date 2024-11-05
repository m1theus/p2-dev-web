package dev.mmmartins.relatoriosapi.service.generator;

import java.util.List;

public interface ArquivoGenerator<T> {
    byte[] generate(final List<String> headers, final List<T> data);
}
