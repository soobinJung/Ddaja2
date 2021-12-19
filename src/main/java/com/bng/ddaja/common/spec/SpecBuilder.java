package com.bng.ddaja.common.spec;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class SpecBuilder {
    public static<T> Builder<T> builder(Class<T> type) {
        return new Builder<T>();
    }

    public static class Builder<T> {
        private List<Specification<T>> specList = new ArrayList<>();

        public Builder<T> addSpec(Specification<T> spec) {
            specList.add(spec);
            return this;
        }

        public Specification<T> toSpecification() {
            if (specList.isEmpty()) return Specification.where(null);
            else if (specList.size() == 1) return specList.get(0);
            else {
                Specification<T> spec = specList.get(0);
                specList.remove(0);
                return specList.stream()
                                .reduce(
                                    spec, (specOne, specTwo) -> specOne.and(specTwo)
                                );
            }
        }
    }
}
