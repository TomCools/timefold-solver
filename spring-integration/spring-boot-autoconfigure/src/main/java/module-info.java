module ai.timefold.solver.spring.boot.autoconfigure {

    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;
    requires spring.boot;

    requires ai.timefold.solver.core;
    requires ai.timefold.solver.benchmark;
    requires spring.jcl;
    requires ai.timefold.solver.jackson;
    requires ai.timefold.solver.test;
    requires com.fasterxml.jackson.databind;
    requires spring.web;
    requires org.jspecify;

}