module ai.timefold.solver.jaxb {
    requires ai.timefold.solver.persistence.common;
    requires ai.timefold.solver.core;
    requires jakarta.xml.bind;

    exports ai.timefold.solver.jaxb.api.score;
}