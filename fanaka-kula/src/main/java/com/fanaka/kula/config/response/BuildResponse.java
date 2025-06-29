package com.fanaka.kula.config.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BuildResponse {

    /**
     * Generic success response:
     * {
     *   "success": true,
     *   "message": "...",
     *   "data": { ... },
     *   "meta": { ... }   // only if non-null
     * }
     */
    public ResponseEntity<Object> success(
            Object data,
            String message,
            Map<String, Object> meta,
            HttpStatus status
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", true);
        body.put("message", message);
        body.put("data", data);
        if (meta != null && !meta.isEmpty()) {
            body.put("meta", meta);
        }
        return ResponseEntity.status(status).body(body);
    }

    /** Convenience overload for non-paginated GETs (default 200 OK, no meta) */
    public ResponseEntity<Object> success(Object data, String message) {
        return success(data, message, null, HttpStatus.OK);
    }

    /**
     * Generic error response:
     * {
     *   "success": false,
     *   "message": "...",
     *   "errors": {
     *     "field_name": "Description of issue",
     *     ...
     *   }
     * }
     */
    public ResponseEntity<Object> error(
            String message,
            Map<String, Object> errors,
            HttpStatus status
    ) {
        Map<String, Object> body = new HashMap<>();
        body.put("success", false);
        body.put("message", message);
        body.put("errors", errors);
        return ResponseEntity.status(status).body(body);
    }

    /** Convenience overload for common validation errors (400 Bad Request) */
    public ResponseEntity<Object> error(String message, Map<String, Object> errors) {
        return error(message, errors, HttpStatus.BAD_REQUEST);
    }

    // — you can leave your old createResponse here if you still need it —
}
