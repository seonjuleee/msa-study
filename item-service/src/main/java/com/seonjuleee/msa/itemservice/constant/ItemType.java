package com.seonjuleee.msa.itemservice.constant;

public enum ItemType {
    FOOD("F", "음식"),
    CLOTHES("C", "옷");

    private String code;
    private String desc;

    ItemType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public boolean hasItemCode(String code) {
        return this.code.equals(code);
    }
}
