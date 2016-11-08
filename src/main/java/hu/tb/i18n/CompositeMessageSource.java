/*
 * Copyright 2015 The original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hu.tb.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractMessageSource;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Message source that resolves the messages by querying the {@link org.vaadin.spring.i18n.MessageProvider}s in
 * the application context. The resolved messages are cached by default. The caching can be turned off.
 *
 * @author Petter Holmström (petter@vaadin.com)
 */
public class CompositeMessageSource extends AbstractMessageSource implements MessageSource {

    public static final String ENV_PROP_MESSAGE_FORMAT_CACHE_ENABLED = "vaadin4spring.i18n.message-format-cache.enabled";
    private static final Logger LOGGER = LoggerFactory.getLogger(CompositeMessageSource.class);
    private final Collection<MessageProvider> messageProviders;
    private final Map<Locale, Map<String, MessageFormat>> messageFormatCache = new ConcurrentHashMap<Locale, Map<String, MessageFormat>>();
    private boolean messageFormatCacheEnabled = true;

    /**
     * Creates a new {@code CompositeMessageSource}.
     *
     * @param applicationContext the application context to use when looking up
     *                           {@link org.vaadin.spring.i18n.MessageProvider}s, must not be {@code null}.
     */
    public CompositeMessageSource(ApplicationContext applicationContext) {
        LOGGER.info("Looking up MessageProviders");
        messageProviders = applicationContext.getBeansOfType(MessageProvider.class).values();
        if (LOGGER.isDebugEnabled()) {
            for (MessageProvider messageProvider : messageProviders) {
                LOGGER.debug("Found MessageProvider [{}]", messageProvider);
            }
        }
        LOGGER.info("Found {} MessageProvider(s)", messageProviders.size());
        setMessageFormatCacheEnabled(applicationContext.getEnvironment()
                .getProperty(ENV_PROP_MESSAGE_FORMAT_CACHE_ENABLED, Boolean.class, true));
    }

    /**
     * Clears the caches of all message providers.
     *
     * @see MessageProvider#clearCache()
     */
    public void clearMessageProviderCaches() {
        for (MessageProvider messageProvider : messageProviders) {
            messageProvider.clearCache();
        }
    }

    /**
     * Returns whether the resolved messages are cached or not. Default is true.
     */
    public boolean isMessageFormatCacheEnabled() {
        return messageFormatCacheEnabled;
    }

    /**
     * Enables or disables caching of resolved messages. This can also be set by the
     * <code>{@value #ENV_PROP_MESSAGE_FORMAT_CACHE_ENABLED}</code>
     * environment property.
     */
    public void setMessageFormatCacheEnabled(boolean messageFormatCacheEnabled) {
        this.messageFormatCacheEnabled = messageFormatCacheEnabled;
        if (messageFormatCacheEnabled) {
            LOGGER.info("MessageFormat cache enabled");
        } else {
            LOGGER.info("MessageFormat cache disabled");
        }
    }

    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        MessageFormat messageFormat = queryCache(s, locale);
        if (messageFormat == null) {
            messageFormat = queryMessageProviders(s, locale);
            if (messageFormat != null) {
                cache(s, locale, messageFormat);
            }
        }
        return messageFormat;
    }

    private MessageFormat queryCache(String s, Locale locale) {
        if (messageFormatCacheEnabled) {
            final Map<String, MessageFormat> cache = getMessageFormatCache(locale);
            return cache.get(s);
        } else {
            return null;
        }
    }

    private void cache(String s, Locale locale, MessageFormat messageFormat) {
        if (messageFormatCacheEnabled) {
            final Map<String, MessageFormat> cache = getMessageFormatCache(locale);
            cache.put(s, messageFormat);
        }
    }

    private MessageFormat queryMessageProviders(String s, Locale locale) {
        LOGGER.debug("Querying message providers for code [{}] for locale [{}]", s, locale);
        for (MessageProvider messageProvider : messageProviders) {
            final MessageFormat messageFormat = messageProvider.resolveCode(s, locale);
            if (messageFormat != null) {
                LOGGER.debug("Code [{}] for locale [{}] found in provider [{}]", s, locale, messageProvider);
                return messageFormat;
            }
        }
        LOGGER.debug("Code [{}] for locale [{}] not found", s, locale);
        return null;
    }

    private Map<String, MessageFormat> getMessageFormatCache(Locale locale) {
        Map<String, MessageFormat> cache = messageFormatCache.get(locale);
        if (cache == null) {
            cache = new ConcurrentHashMap<String, MessageFormat>();
            messageFormatCache.put(locale, cache);
        }
        return cache;
    }
}
