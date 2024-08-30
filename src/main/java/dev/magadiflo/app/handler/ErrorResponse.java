package dev.magadiflo.app.handler;

import java.util.Map;

public record ErrorResponse<T>(Map<String, T> errors) {
}
