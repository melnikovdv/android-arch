package com.example.arch.util;

import java.util.Random;

public class Generator {

    private final Random random = new Random();

    public String string(int length) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public long timeoutMillis(int limit) {
        return random.nextInt(limit) * 1000 + 300;
    }

    public int nextInt(int limit) {
        return random.nextInt(limit);
    }
}
