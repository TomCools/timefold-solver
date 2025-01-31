package ai.timefold.solver.core.api.domain.valuerange;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;

import ai.timefold.solver.core.api.domain.variable.PlanningVariable;
import ai.timefold.solver.core.api.solver.SolverFactory;

import org.jspecify.annotations.NonNull;

/**
 * Provides the planning values that can be used for a {@link PlanningVariable}.
 * <p>
 * This is specified on a getter of a java bean property (or directly on a field)
 * which returns a {@link Collection} or {@link ValueRange}.
 * A {@link Collection} is implicitly converted to a {@link ValueRange}.
 */
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
public @interface ValueRangeProvider {

    /**
     * Used by {@link PlanningVariable#valueRangeProviderRefs()}
     * to map a {@link PlanningVariable} to a {@link ValueRangeProvider}.
     * If not provided, an attempt will be made to find a matching {@link PlanningVariable} without a ref.
     *
     * @return if provided, must be unique across a {@link SolverFactory}
     */
    @NonNull
    String id() default "";

}
