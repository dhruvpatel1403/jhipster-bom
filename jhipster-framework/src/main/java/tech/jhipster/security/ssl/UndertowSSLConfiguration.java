/*
 * Copyright 2016-2025 the original author or authors from the JHipster project.
 *
 * This file is part of the JHipster project, see https://www.jhipster.tech/
 * for more information.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.jhipster.security.ssl;

import io.undertow.UndertowOptions;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * SSL configuration for Undertow.
 * <p>
 * SSL_USER_CIPHER_SUITES_ORDER : It will force the cipher suite defined by the user,
 * allowing to achieve perfect forward secrecy.
 * This can only be activated with HTTPS and a cipher suite defined by the user (server.ssl.ciphers).
 * <p>
 * Please note that when using JHipster, you can use the `server.ssl.ciphers` property that is available
 * in your `application-tls.yml` file, and which is ready to work with this configuration.
 *
 * @see <a href="https://github.com/ssllabs/research/wiki/SSL-and-TLS-Deployment-Best-Practices#25-use-forward-secrecy" target="_blank">More explanation on perfect forward secrecy</a>
 */
@Configuration
@ConditionalOnBean(UndertowServletWebServerFactory.class)
@ConditionalOnClass(UndertowOptions.class)
public class UndertowSSLConfiguration extends BaseSSLConfiguration {

    private final UndertowServletWebServerFactory factory;

    public UndertowSSLConfiguration(UndertowServletWebServerFactory undertowServletWebServerFactory) {
        super(LoggerFactory.getLogger(UndertowSSLConfiguration.class));
        this.factory = undertowServletWebServerFactory;
        configureCipherSuiteOrder();
    }

    @Override
    protected void applyCipherSuiteConfiguration() {
        factory.addBuilderCustomizers(builder ->
            builder.setSocketOption(UndertowOptions.SSL_USER_CIPHER_SUITES_ORDER, Boolean.TRUE)
        );
    }
}
