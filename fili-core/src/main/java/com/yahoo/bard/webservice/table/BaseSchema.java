// Copyright 2017 Yahoo Inc.
// Licensed under the terms of the Apache license. Please see LICENSE.md file distributed with this work for terms.
package com.yahoo.bard.webservice.table;

import com.yahoo.bard.webservice.druid.model.query.Granularity;

import com.google.common.collect.Sets;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A parent class for most schema implementations.
 */
public class BaseSchema implements Schema {

    private final LinkedHashSet<Column> columns;
    private final Granularity granularity;

    /**
     * Constructor.
     *
     * @param granularity  The granularity for this schema.
     * @param columns  The columns for this schema.
     */
    protected BaseSchema(Granularity granularity, Iterable<Column> columns) {
        this.granularity = granularity;
        this.columns = Sets.newLinkedHashSet(columns);
    }

    @Override
    public LinkedHashSet<Column> getColumns() {
        return columns;
    }

    /**
     * Get the names of the columns returned by getColumns method.
     *
     * @return linked hash set of column names in this schema
     */
    public LinkedHashSet<String> getColumnNames() {
        return getColumns().stream()
                .map(Column::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Override
    public Granularity getGranularity() {
        return granularity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseSchema)) {
            return false;
        }

        BaseSchema that = (BaseSchema) o;
        return this.getClass() == o.getClass()
                && Objects.equals(columns, that.columns)
                && Objects.equals(granularity, that.getGranularity()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(granularity, columns);
    }
}
