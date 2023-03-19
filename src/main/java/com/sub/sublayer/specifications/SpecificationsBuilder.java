package com.sub.sublayer.specifications;

import com.sub.sublayer.models.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationsBuilder {
    private final List<SpecSearchCriteria> params;

    public SpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public final SpecificationsBuilder with(String key, String operation, Object value) {
        return with(null, key, operation, value);
    }

    public final SpecificationsBuilder with(String orPredicate, String key, String operation,
                                            Object value) {
        boolean orCondition=false;

        orCondition=orPredicate=="OR"?true:false;

        params.add(new SpecSearchCriteria(orCondition, key, operation, value));
        return this;
    }

    public Specification build() {
        if (params.size() == 0)
            return null;

        Specification result = new EntitySpecification<User>(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new EntitySpecification<User>(params.get(i)))
                    : Specification.where(result).and(new EntitySpecification<User>(params.get(i)));
        }
        return result;
    }
}
