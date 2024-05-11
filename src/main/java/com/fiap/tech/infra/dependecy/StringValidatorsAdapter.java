package com.fiap.tech.infra.dependecy;

import java.util.UUID;

public class StringValidatorsAdapter {
    public static boolean isValidUUID(String uuidString) {
        if (uuidString == null) {
            return false;
        }
        try {
            UUID.fromString(uuidString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
