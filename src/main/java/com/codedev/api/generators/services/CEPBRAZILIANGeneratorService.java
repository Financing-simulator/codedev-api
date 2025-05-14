package com.codedev.api.generators.services;

import java.util.*;

public class CEPBRAZILIANGeneratorService {
    private static final Map<String, List<int[]>> statePostalRanges = new HashMap<>();
    private static final Random random = new Random();

    static {
        statePostalRanges.put("AC", Arrays.asList(new int[]{69900, 69999}));
        statePostalRanges.put("AL", Arrays.asList(new int[]{57000, 57999}));
        statePostalRanges.put("AP", Arrays.asList(new int[]{68900, 68999}));
        statePostalRanges.put("AM", Arrays.asList(new int[]{69000, 69299}));
        statePostalRanges.put("BA", Arrays.asList(new int[]{40000, 48999}));
        statePostalRanges.put("CE", Arrays.asList(new int[]{60000, 63999}));
        statePostalRanges.put("DF", Arrays.asList(new int[]{70000, 72799}, new int[]{73000, 73699}));
        statePostalRanges.put("ES", Arrays.asList(new int[]{29000, 29999}));
        statePostalRanges.put("GO", Arrays.asList(new int[]{72800, 72999}, new int[]{73700, 76799}));
        statePostalRanges.put("MA", Arrays.asList(new int[]{65000, 65999}));
        statePostalRanges.put("MT", Arrays.asList(new int[]{78000, 78899}));
        statePostalRanges.put("MS", Arrays.asList(new int[]{79000, 79999}));
        statePostalRanges.put("MG", Arrays.asList(new int[]{30000, 39999}));
        statePostalRanges.put("PA", Arrays.asList(new int[]{66000, 68899}));
        statePostalRanges.put("PB", Arrays.asList(new int[]{58000, 58999}));
        statePostalRanges.put("PR", Arrays.asList(new int[]{80000, 87999}));
        statePostalRanges.put("PE", Arrays.asList(new int[]{50000, 56999}));
        statePostalRanges.put("PI", Arrays.asList(new int[]{64000, 64999}));
        statePostalRanges.put("RJ", Arrays.asList(new int[]{20000, 28999}));
        statePostalRanges.put("RN", Arrays.asList(new int[]{59000, 59999}));
        statePostalRanges.put("RS", Arrays.asList(new int[]{90000, 99999}));
        statePostalRanges.put("RO", Arrays.asList(new int[]{76800, 76999}));
        statePostalRanges.put("RR", Arrays.asList(new int[]{69300, 69399}));
        statePostalRanges.put("SC", Arrays.asList(new int[]{88000, 89999}));
        statePostalRanges.put("SP", Arrays.asList(new int[]{1000, 19999})); // SP: 01000–19999
        statePostalRanges.put("SE", Arrays.asList(new int[]{49000, 49999}));
        statePostalRanges.put("TO", Arrays.asList(new int[]{77000, 77999}));
    }

    public static String generatePostalCode() {
        return generatePostalCode(null);
    }

    public static String generatePostalCode(String stateAbbreviation) {
        List<int[]> ranges;

        if (stateAbbreviation != null && statePostalRanges.containsKey(stateAbbreviation.toUpperCase())) {
            ranges = statePostalRanges.get(stateAbbreviation.toUpperCase());
        } else if (stateAbbreviation == null) {
            // Combine all state ranges
            ranges = new ArrayList<>();
            for (List<int[]> rangeList : statePostalRanges.values()) {
                ranges.addAll(rangeList);
            }
        } else {
            throw new IllegalArgumentException("Unrecognized state: " + stateAbbreviation);
        }

        int[] selectedRange = ranges.get(random.nextInt(ranges.size()));
        int start = selectedRange[0];
        int end = selectedRange[1];
        int prefix = random.nextInt(end - start + 1) + start;
        int suffix = random.nextInt(1000); // 3-digit suffix

        return String.format("%05d-%03d", prefix, suffix);
    }


}
