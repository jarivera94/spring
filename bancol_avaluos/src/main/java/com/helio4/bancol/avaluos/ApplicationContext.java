package com.helio4.bancol.avaluos;

import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.faces.mvc.JsfView;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.helio4.bancol.avaluos.controlador.AvaluosAuthenticationFailureHandler;
import com.helio4.bancol.avaluos.controlador.AvaluosAuthenticationProvider;
import com.helio4.bancol.avaluos.controlador.AvaluosAuthenticationSuccessHandler;
import com.helio4.bancol.avaluos.servicio.util.Constantes;
import com.zaxxer.hikari.HikariDataSource;
import java.util.logging.Level;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Configuration
@EnableTransactionManagement
@EnableWebSecurity
@EnableJpaRepositories("com.helio4.bancol.avaluos.persistencia")
@ComponentScan(basePackages = { "com.helio4.bancol.avaluos.controlador", "com.helio4.bancol.avaluos.ensamblador",
                "com.helio4.bancol.avaluos.dominio", "com.helio4.bancol.avaluos.persistencia",
                "com.helio4.bancol.avaluos.servicio" })
@ImportResource("classpath:spring/applicationContext.xml")
@PropertySource("classpath:properties/application.properties")
public class ApplicationContext extends WebSecurityConfigurerAdapter {

        private static Logger log = LoggerFactory.getLogger(ApplicationContext.class);
        /**
         * Propiedades para especificar la carpeta donde se almacenaran las paginas JSF.
         */
        private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/pages/";
        private static final String VIEW_RESOLVER_SUFFIX = ".xhtml";

        /**
         * Nombre de las propiedades de la base de datos en el archivo de propiedades
         * application.properties.
         */
        private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
        private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
        private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
        private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
        private static final String PROPERTY_NAME_HIBERNATE_DEFAULT_SCHEMA = "hibernate.default_schema";
        private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
        private static final String PROPERTY_NAME_HIBERNATE_CACHE_REGION_FACTORY_CLASS = "hibernate.cache.region.factory_class";
        private static final String PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
        private static final String PROPERTY_NAME_HIBERNATE_CACHE_USE_QUERY_CACHE = "hibernate.cache.use_query_cache";
        private static final String PROPERTY_SHARED_CACHE_MODE = "spring.jpa.properties.javax.persistence.sharedCache.mode";
        private static final String PROPERTY_NAME_URL = "dataSource.url";
        private static final String PROPERTY_NAME_JNDI = "dataSource.jndi";
        private static final String PROPERTY_NAME_USER = "dataSource.user";
        private static final String PROPERTY_NAME_PASS = "dataSource.password";

        /**
         * Nombre de las propiedades de mensajes en el archivo de propiedades.
         */
        private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";

        @Resource
        private Environment environment;

        /**
         * Bean del origen de datos.
         * 
         * @return El origen de datos
         */
        @Bean
        public DataSource dataSource() {
                DataSource ds = null;
                try {
                        log.info("Inicializando datasource de hikari");
                        // String username = environment.getRequiredProperty(PROPERTY_NAME_USER);
                        String url = environment.getRequiredProperty(PROPERTY_NAME_JNDI);
                        // String password = environment.getRequiredProperty(PROPERTY_NAME_PASS);
                        Context initContext = new InitialContext();
                        ds = (DataSource) initContext.lookup(url);

                        // HikariDataSource ds = new HikariDataSource();
                        // ds.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
                        // ds.setMaximumPoolSize(20);
                        // ds.setConnectionTimeout(20000);
                        // ds.setMaxLifetime(1800000);
                        // ds.setLeakDetectionThreshold(10000);
                        // ds.addDataSourceProperty("url", url);
                        // ds.addDataSourceProperty("user", username);
                        // ds.addDataSourceProperty("password", password);
                        // ds.setPoolName("bancol_avaluos_pool");
                        // return ds;
                } catch (NamingException ex) {
                        java.util.logging.Logger.getLogger(ApplicationContext.class.getName()).log(Level.SEVERE, null,
                                        ex);
                }
                return ds;
        }

        /**
         * Bean del manejador de transacciones de la base de datos.
         * 
         * @return El manejador de transacciones.
         * @throws ClassNotFoundException
         */
        @Bean
        public JpaTransactionManager transactionManager() throws ClassNotFoundException {
                JpaTransactionManager transactionManager = new JpaTransactionManager();

                transactionManager.setEntityManagerFactory(entityManagerFactory());

                return transactionManager;
        }

        /**
         * Fabrica abastracta de creación de los Beans de origen de datos y conexion con
         * el motor de base de datos.
         * 
         * @return Fabrica administradora de entidades.
         * @throws ClassNotFoundException
         */
        @Bean
        public EntityManagerFactory entityManagerFactory() throws ClassNotFoundException {
                HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
                hibernateJpaVendorAdapter.setGenerateDdl(true);
                LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

                entityManagerFactoryBean.setDataSource(dataSource());
                entityManagerFactoryBean.setPackagesToScan(
                                environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
                entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
                entityManagerFactoryBean.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);

                Properties jpaProterties = new Properties();
                jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
                                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
                jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
                                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
                jpaProterties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY,
                                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
                jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
                                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
                jpaProterties.put(PROPERTY_NAME_HIBERNATE_DEFAULT_SCHEMA,
                                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DEFAULT_SCHEMA));
                jpaProterties.put(PROPERTY_NAME_HIBERNATE_CACHE_REGION_FACTORY_CLASS,
                                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_REGION_FACTORY_CLASS));
                jpaProterties.put(PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE,
                                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
                jpaProterties.put("hibernate.connection.release_mode", "after_transaction");
                jpaProterties.put(PROPERTY_NAME_HIBERNATE_CACHE_USE_QUERY_CACHE,
                                environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_USE_QUERY_CACHE));
                jpaProterties.put(PROPERTY_SHARED_CACHE_MODE,
                                environment.getRequiredProperty(PROPERTY_SHARED_CACHE_MODE));

                entityManagerFactoryBean.setJpaProperties(jpaProterties);
                entityManagerFactoryBean.afterPropertiesSet();
                return entityManagerFactoryBean.getObject();
        }

        /**
         *
         * @return
         */
        @Bean
        public MessageSource messageSource() {
                ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
                messageSource.setBasename(environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_BASENAME));
                return messageSource;
        }

        /**
         * Resolvedor de vistas.
         * 
         * @return el resolvedor de vistas.
         */
        @Bean
        public ViewResolver viewResolver() {
                UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
                viewResolver.setViewClass(JsfView.class);
                viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
                viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
                return viewResolver;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.authenticationProvider(authenticactionProvider());
        }

        @Bean
        public AvaluosAuthenticationProvider authenticactionProvider() {
                return new AvaluosAuthenticationProvider();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                                .antMatchers("/javax.faces.resource/**", "/signup", "/javax.faces.resource/jquery/*",
                                                "/rest/**")
                                .permitAll()
                                // .antMatchers("/pages/configuracion/configuracion.xhtml")
                                // .hasRole("")
                                .antMatchers("/pages/configuracion/entidades.xhtml")
                                .hasRole(Constantes.PERMISO_CREAR_ENTIDADES)
                                .antMatchers("/pages/configuracion/segmentos.xhtml")
                                .hasRole(Constantes.PERMISO_CREAR_SEGMENTOS)
                                .antMatchers("/pages/configuracion/sucursales.xhtml")
                                .hasRole(Constantes.PERMISO_CREAR_SUCURSALES).antMatchers("/pages/configuracion/rol/**")
                                .hasRole(Constantes.PERMISO_CREAR_ROL).antMatchers("/pages/configuracion/usuarios/**")
                                .access("hasRole('ROLE_Crear Usuario') " + "or hasRole('ROLE_Crear Abogado')")
                                .antMatchers("/pages/configuracion/clientes/editarCliente.xhtml")
                                .access("hasRole('ROLE_" + Constantes.PERMISO_EDITAR_CLIENTE + "')")
                                .antMatchers("/pages/solicitudes/avaluo.xhtml")
                                .access("hasRole('ROLE_" + Constantes.PERMISO_CREAR_NUEVA_SOLICITUD + "')"
                                                + " or hasRole('ROLE_" + Constantes.PERMISO_EDITAR_SOLICITUD + "')"
                                                + " or hasRole('ROLE_" + Constantes.PERMISO_ABOGADO + "')")
                                .antMatchers("/pages/avaluos/asignarPerito.xhtml")
                                .access("hasRole('ROLE_" + Constantes.PERMISO_ASIGNAR_SOLICITUD + "')"
                                                + " or hasRole('ROLE_" + Constantes.PERMISO_REASIGNAR_SOLICITUD + "')")
                                .antMatchers("/pages/avaluos/programarCita.xhtml")
                                .access("hasRole('ROLE_" + Constantes.PERMISO_PROGRAMAR_CITA + "')"
                                                + " or hasRole('ROLE_" + Constantes.PERMISO_REPROGRAMAR_CITA + "')")
                                .antMatchers("/pages/informes/**")
                                .access("hasRole('ROLE_" + Constantes.PERMISO_INGRESAR_INFORME + "')"
                                                + " or hasRole('ROLE_" + Constantes.PERMISO_REVISAR_GUARDAR_AVALUO
                                                + "')" + " or hasRole('ROLE_"
                                                + Constantes.PERMISO_VER_CORRECCIONES_SOLICITADAS + "')"
                                                + " or hasRole('ROLE_" + Constantes.PERMISO_CORREGIR_INFORME + "')"
                                                + " or hasRole('ROLE_"
                                                + Constantes.PERMISO_EDITAR_AVALUO_DESPUES_DE_APROBADO + "')"
                                                + " or hasRole('ROLE_" + Constantes.PERMISO_VER_INFORMES_SIN_EDITAR
                                                + "')")
                                .antMatchers("/pages/informes/subirFotos.xhtml")
                                .access("hasRole('ROLE_" + Constantes.PERMISO_CARGAR_FOTOS + "')" + " or hasRole('ROLE_"
                                                + Constantes.PERMISO_VER_FOTOS + "')")
                                .antMatchers("/pages/exportarArchivoBua.xhtml").hasRole(Constantes.PERMISO_EXPORTAR_BUA)
                                .antMatchers("/pages/estudiosmercado.xhtml").hasRole(Constantes.PERMISO_ESTUDIO_MERCADO)
                                .antMatchers("/pages/avaluos/reportes.xhtml")
                                .hasRole(Constantes.PERMISO_EXPORTAR_REPORTES)
                                .antMatchers("/pages/configuracion/admin.xhtml")
                                .hasRole(Constantes.PERMISO_EXPORTAR_REPORTES).anyRequest().authenticated().and()
                                .formLogin().loginPage("/login.xhtml").failureHandler(authenticationFailureHandler())
                                .usernameParameter("username").passwordParameter("inlineContrasena")

                                .loginProcessingUrl("/login.do").successHandler(authenticationSuccessHandler())
                                .permitAll().and().logout().invalidateHttpSession(true).logoutUrl("/logout.do")
                                .logoutSuccessUrl("/login.xhtml").permitAll();
                http.csrf().disable();
                http.sessionManagement().invalidSessionUrl("/login.xhtml").maximumSessions(99)
                                .sessionRegistry(sessionRegistry());
        }

        @Bean
        public SessionRegistry sessionRegistry() {
                return new SessionRegistryImpl();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationFailureHandler authenticationFailureHandler() {
                return new AvaluosAuthenticationFailureHandler();
        }

        @Bean
        public RedirectStrategy redirectStrategy() {
                return new DefaultRedirectStrategy();
        }

        @Bean
        public AuthenticationSuccessHandler authenticationSuccessHandler() {
                return new AvaluosAuthenticationSuccessHandler();
        }

}
