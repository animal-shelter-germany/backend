package org.tiere.util.query_builder;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.tiere.util.query_builder.section.QueryCustomSection;
import org.tiere.util.query_builder.section.QueryElement;
import org.tiere.util.query_builder.section.QuerySection;

import java.util.*;

public class QueryBuilder<E> {

    private final List<QueryElement> querySections;
    private final Map<String, Object> params;

    public static <E> QueryBuilder<E> build() {
        return new QueryBuilder<E>();
    }

    private QueryBuilder() {
        querySections = new LinkedList<>();
        params = new HashMap<>();
    }

    public QueryBuilder<E> add(QueryCustomSection queryCustomSection) {
        querySections.add(queryCustomSection);
        for(Map.Entry<String, Object> entry : queryCustomSection.getParams().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(!params.containsKey(key)) {
                params.put(key, value);
            } else {
                throw new RuntimeException("Parameter with name " + key + " already in use.");
            }
        }
        return this;
    }

    public QueryBuilder<E> add(QuerySection querySection) {
        querySections.add(querySection);
        params.put(querySection.getQueryParam(), querySection.getQueryValue());
        return this;
    }

    public PanacheQuery<E> execute(PanacheRepositoryBase<E, ?> repository) {
        return repository.find(buildQuery(), params);
    }

    public String buildQuery() {
        StringBuilder query = new StringBuilder();
        Iterator<QueryElement> iterator = querySections.iterator();
        while(iterator.hasNext()) {
            query.append(iterator.next().toQuery());
            if(iterator.hasNext()) {
               query.append(" and ");
            }
        }
        return query.toString();
    }
}
