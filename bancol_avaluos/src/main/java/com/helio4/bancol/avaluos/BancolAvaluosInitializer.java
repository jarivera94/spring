package com.helio4.bancol.avaluos;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class BancolAvaluosInitializer
        extends AbstractSecurityWebApplicationInitializer {

    public BancolAvaluosInitializer() {
        super(ApplicationContext.class);
    }

}
