package com.adelphipartners.hrapi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Role {

    CEO("Chief Executive Officer", 0, new BigDecimal("25000.00")),
    MARKETING_VP("Marketing Department Head", 1, new BigDecimal("15000.00")),
    SALES_VP("Sales Head", 1, new BigDecimal("15000.00")), ENGINEERING_VP(
            "Engineering Department Head", 1,
            new BigDecimal("15000" + ".00")), HUMAN_RESOURCES_VP("HR " +
            "Department Head", 1, new BigDecimal("15000.00")),
    SR_PARTNERSHIPS_VP("SR & Partnerships Department Head", 1,
            new BigDecimal("15000.00")), EMPLOYEE("Entry Level Employee", 2,
            new BigDecimal("10000.00"));

    private static final Map<Integer, List<Role>> BY_CLEARANCE_LEVEL =
            new HashMap<>();
    private static final Map<String, Role> BY_LABEL = new HashMap<>();

    static {
        for (Role role : values()) {
            List<Role> tempArrayList;
            if (BY_CLEARANCE_LEVEL.containsKey(role.clearanceLevel)) {
                tempArrayList = BY_CLEARANCE_LEVEL.get(role.clearanceLevel);
            } else {
                tempArrayList = new ArrayList<>();
            }
            tempArrayList.add(role);
            BY_CLEARANCE_LEVEL.put(role.clearanceLevel, tempArrayList);

            BY_LABEL.put(role.label, role);
        }
    }

    public final String label;
    public final int clearanceLevel;
    public final BigDecimal baseSalaryPerMonth;

    private Role(String label, int clearanceLevel,
                 BigDecimal baseSalaryPerMonth) {
        this.label = label;
        this.clearanceLevel = clearanceLevel;
        this.baseSalaryPerMonth = baseSalaryPerMonth;
    }

    public static Role valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static List<Role> valueOfClearanceLevel(int clearanceLevel) {
        return BY_CLEARANCE_LEVEL.get(clearanceLevel);
    }

    @Override
    public String toString() {
        return this.label + " : " + this.clearanceLevel + " : " + this.baseSalaryPerMonth;
    }

}
