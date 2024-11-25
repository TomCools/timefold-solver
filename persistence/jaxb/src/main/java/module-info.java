open module ai.timefold.solver.jaxb {
    exports ai.timefold.solver.jaxb.api.score;

    requires ai.timefold.solver.core;
    requires ai.timefold.solver.persistence.common;

    requires org.jspecify;
    requires jakarta.xml.bind;
}