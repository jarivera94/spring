package com.helio4.bancol.avaluos.controlador;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

public class RememberMePostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		if (bean instanceof AbstractRememberMeServices) {
			AbstractRememberMeServices rememberMe = (AbstractRememberMeServices) bean;
			rememberMe.setParameter("_spring_security_remember_me_input");
		}
		return bean;
	}

}
