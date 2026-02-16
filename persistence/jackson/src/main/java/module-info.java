open module ai.timefold.solver.jackson {
    exports ai.timefold.solver.jackson.impl.domain.solution;
    exports ai.timefold.solver.jackson.api;

    requires ai.timefold.solver.core;
    requires ai.timefold.solver.persistence.common;

    requires org.jspecify;
    requires tools.jackson.databind;
}