package com.tc.training.pizzaDelivery.enums;

public enum DealType {
    BOGO("Buy One Get One"),
    FLAT_DISCOUNT("Flat Discount"),
    BUNDLE("Bundle"),
    CATEGORY("Category"),
    THRESHOLD("Threshold");

    private final String displayName;

    DealType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

//    DealType dealType = DealType.BOGO;
//    String displayName = dealType.getDisplayName();
//    System.out.println("Deal Type: " + displayName); // Output: Deal Type: Buy One Get One
