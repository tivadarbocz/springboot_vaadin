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
package hu.tb.i18n.annotation;

import hu.tb.i18n.config.I18NConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Add this annotation to your application configuration to enable the {@link org.vaadin.spring.i18n.I18N} internationalization support.
 *
 * @author Gert-Jan Timmer (gjr.timmer@gmail.com)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(I18NConfiguration.class)
public @interface EnableI18N {

}
