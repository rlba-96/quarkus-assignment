package com.labseq;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class LabseqService {

    private final ConcurrentHashMap<Integer, Long> cache = new ConcurrentHashMap<>();

    public LabseqService() {
        cache.put(0, 0L);
        cache.put(1, 1L);
        cache.put(2, 0L);
        cache.put(3, 1L);
    }

    public long getLabseqValue(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be positive");
        }

        // Return cached if already computed
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        // Iteratively compute up to n
        for (int i = 4; i <= n; i++) {
            cache.computeIfAbsent(i, k -> cache.get(k - 4) + cache.get(k - 3));
        }

        return cache.get(n);
    }
}
