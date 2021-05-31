package com.intelycare.health;
import com.codahale.metrics.health.HealthCheck;
import com.intelycare.core.Template;

import java.util.Optional;

public class TemplateHealthCheck extends HealthCheck {
    private final Template template;

    public TemplateHealthCheck(Template template) {
        this.template = template;
    }

//    @Override
//    protected Result check() throws Exception {
//        final String saying = String.format(template, "TEST");
//        if (!saying.contains("TEST")) {
//            return Result.unhealthy("template doesn't include a name");
//        }
//        return Result.healthy();
//    }


    @Override
    protected Result check() throws Exception {
        template.render(Optional.of("woo"));
        template.render(Optional.empty());
        return Result.healthy();
    }
}
