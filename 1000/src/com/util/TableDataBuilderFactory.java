package com.util;

import java.util.Map;

public class TableDataBuilderFactory {

    private Map<String, TableDataBuilder> builders;

    public TableDataBuilder getBuilder(String key) {
        return builders.get(key);
    }

    public Map<String, TableDataBuilder> getBuilders() {
        return builders;
    }

    public void setBuilders(Map<String, TableDataBuilder> builders) {
        this.builders = builders;
    }
}
