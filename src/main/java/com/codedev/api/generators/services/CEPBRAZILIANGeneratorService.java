package com.codedev.api.generators.services;

import java.util.*;

public class CEPBRAZILIANGeneratorService {
    private static final Map<String, List<int[]>> STATE_POSTAL_RANGES = new HashMap<>();
    private static final Random RANDOM = new Random();

    static {
        STATE_POSTAL_RANGES.put("AC", List.of(new int[]{69900, 69999}));
        STATE_POSTAL_RANGES.put("AL", List.of(new int[]{57000, 57999}));
        STATE_POSTAL_RANGES.put("AP", List.of(new int[]{68900, 68999}));
        STATE_POSTAL_RANGES.put("AM", List.of(new int[]{69000, 69299}));
        STATE_POSTAL_RANGES.put("BA", List.of(new int[]{40000, 48999}));
        STATE_POSTAL_RANGES.put("CE", List.of(new int[]{60000, 63999}));
        STATE_POSTAL_RANGES.put("DF", List.of(new int[]{70000, 72799}, new int[]{73000, 73699}));
        STATE_POSTAL_RANGES.put("ES", List.of(new int[]{29000, 29999}));
        STATE_POSTAL_RANGES.put("GO", List.of(new int[]{72800, 72999}, new int[]{73700, 76799}));
        STATE_POSTAL_RANGES.put("MA", List.of(new int[]{65000, 65999}));
        STATE_POSTAL_RANGES.put("MT", List.of(new int[]{78000, 78899}));
        STATE_POSTAL_RANGES.put("MS", List.of(new int[]{79000, 79999}));
        STATE_POSTAL_RANGES.put("MG", List.of(new int[]{30000, 39999}));
        STATE_POSTAL_RANGES.put("PA", List.of(new int[]{66000, 68899}));
        STATE_POSTAL_RANGES.put("PB", List.of(new int[]{58000, 58999}));
        STATE_POSTAL_RANGES.put("PR", List.of(new int[]{80000, 87999}));
        STATE_POSTAL_RANGES.put("PE", List.of(new int[]{50000, 56999}));
        STATE_POSTAL_RANGES.put("PI", List.of(new int[]{64000, 64999}));
        STATE_POSTAL_RANGES.put("RJ", List.of(new int[]{20000, 28999}));
        STATE_POSTAL_RANGES.put("RN", List.of(new int[]{59000, 59999}));
        STATE_POSTAL_RANGES.put("RS", List.of(new int[]{90000, 99999}));
        STATE_POSTAL_RANGES.put("RO", List.of(new int[]{76800, 76999}));
        STATE_POSTAL_RANGES.put("RR", List.of(new int[]{69300, 69399}));
        STATE_POSTAL_RANGES.put("SC", List.of(new int[]{88000, 89999}));
        STATE_POSTAL_RANGES.put("SP", List.of(new int[]{1000, 19999})); // SP: 01000–19999
        STATE_POSTAL_RANGES.put("SE", List.of(new int[]{49000, 49999}));
        STATE_POSTAL_RANGES.put("TO", List.of(new int[]{77000, 77999}));
    }

    public static String generatePostalCode() {
        return generatePostalCode(null);
    }

    public static String generatePostalCode(String stateAbbreviation) {
        List<int[]> ranges;

        if (stateAbbreviation != null && STATE_POSTAL_RANGES.containsKey(stateAbbreviation.toUpperCase())) {
            ranges = STATE_POSTAL_RANGES.get(stateAbbreviation.toUpperCase());
        } else if (stateAbbreviation == null) {
            // Combine all state ranges
            ranges = new ArrayList<>();
            for (List<int[]> rangeList : STATE_POSTAL_RANGES.values()) {
                ranges.addAll(rangeList);
            }
        } else {
            throw new IllegalArgumentException("Unrecognized state: " + stateAbbreviation);
        }

        int[] selectedRange = ranges.get(RANDOM.nextInt(ranges.size()));
        int start = selectedRange[0];
        int end = selectedRange[1];
        int prefix = RANDOM.nextInt(end - start + 1) + start;
        int suffix = RANDOM.nextInt(1000); // 3-digit suffix

        return String.format("%05d-%03d", prefix, suffix);
    }

}
