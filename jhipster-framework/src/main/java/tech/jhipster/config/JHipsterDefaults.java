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

package tech.jhipster.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>JHipsterDefaults interface.</p>
 */
@SuppressWarnings("java:S2386")
public interface JHipsterDefaults {
    interface Async {
        int corePoolSize = 2;
        int maxPoolSize = 50;
        int queueCapacity = 10000;
    }

    interface Http {
        interface Cache {
            int timeToLiveInDays = 1461; // 4 years (including leap day)
        }
    }

    interface Cache {
        interface Hazelcast {
            int timeToLiveSeconds = 3600; // 1 hour
            int backupCount = 1;

            interface ManagementCenter {
                boolean enabled = false;
                int updateInterval = 3;
                String url = "";
            }
        }

        interface Caffeine {
            int timeToLiveSeconds = 3600; // 1 hour
            long maxEntries = 100;
        }

        interface Ehcache {
            int timeToLiveSeconds = 3600; // 1 hour
            long maxEntries = 100;
        }

        interface Infinispan {
            String configFile = "default-configs/default-jgroups-tcp.xml";
            boolean statsEnabled = false;

            interface Local {
                long timeToLiveSeconds = 60; // 1 minute
                long maxEntries = 100;
            }

            interface Distributed {
                long timeToLiveSeconds = 60; // 1 minute
                long maxEntries = 100;
                int instanceCount = 1;
            }

            interface Replicated {
                long timeToLiveSeconds = 60; // 1 minute
                long maxEntries = 100;
            }
        }

        interface Memcached {
            boolean enabled = false;
            String servers = "localhost:11211";
            int expiration = 300; // 5 minutes
            boolean useBinaryProtocol = true;

            interface Authentication {
                boolean enabled = false;
            }
        }

        interface Redis {
            String[] server = { "redis://localhost:6379" };
            int expiration = 300; // 5 minutes
            boolean cluster = false;
            int connectionPoolSize = 64; // default as in redisson
            int connectionMinimumIdleSize = 24; // default as in redisson
            int subscriptionConnectionPoolSize = 50; // default as in redisson
            int subscriptionConnectionMinimumIdleSize = 1; // default as in redisson
        }
    }

    interface Mail {
        boolean enabled = false;
        String from = "";
        String baseUrl = "";
    }

    interface Security {
        String contentSecurityPolicy =
            "default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:";

        interface ClientAuthorization {
            String accessTokenUri = null;
            String tokenServiceId = null;
            String clientId = null;
            String clientSecret = null;
        }

        interface Authentication {
            interface Jwt {
                String secret = null;
                String base64Secret = null;
                long tokenValidityInSeconds = 1800; // 30 minutes
                long tokenValidityInSecondsForRememberMe = 2592000; // 30 days
            }
        }

        interface RememberMe {
            String key = null;
        }
    }

    interface ApiDocs {
        String title = "Application API";
        String description = "API documentation";
        String version = "0.0.1";
        String termsOfServiceUrl = null;
        String contactName = null;
        String contactUrl = null;
        String contactEmail = null;
        String license = null;
        String licenseUrl = null;
        String[] defaultIncludePattern = { "/api/**" };
        String[] managementIncludePattern = { "/management/**" };
        String host = null;
        String[] protocols = {};
        boolean useDefaultResponseMessages = true;
    }

    interface Logging {
        boolean useJsonFormat = false;

        interface Logstash {
            boolean enabled = false;
            String host = "localhost";
            int port = 5000;
            int ringBufferSize = 512;
        }
    }

    interface Social {
        String redirectAfterSignIn = "/#/home";
    }

    interface Gateway {
        Map<String, List<String>> authorizedMicroservicesEndpoints = new LinkedHashMap<>();

        interface RateLimiting {
            boolean enabled = false;
            long requestsPerHourLimit = 100_000L;
            int windowInSeconds = 3_600;
        }
    }

    interface Ribbon {
        String[] displayOnActiveProfiles = null;
    }

    interface Registry {
        String password = null;
    }

    interface ClientApp {
        String name = "jhipsterApp";
    }

    interface AuditEvents {
        int retentionPeriod = 30;
    }
}
