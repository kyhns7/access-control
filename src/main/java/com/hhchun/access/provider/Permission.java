package com.hhchun.access.provider;

import java.util.Objects;

public class Permission {
    /**
     * 权限的记号标识
     */
    private final String symbol;

    public Permission(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}
