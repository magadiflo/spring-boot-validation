package dev.magadiflo.app.handler;

import java.util.Map;

public record ErrorResponse(Map<String, String> errors) {
}
