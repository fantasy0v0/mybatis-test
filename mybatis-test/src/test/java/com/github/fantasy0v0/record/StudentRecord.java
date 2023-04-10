package com.github.fantasy0v0.record;

import java.util.Map;

public record StudentRecord(long id,
                            String name,
                            Map<String, String> ext) {
}
