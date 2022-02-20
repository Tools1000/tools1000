
package com.github.ktools1000.fx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LazyLoadingBooleanProperty extends LazyLoadingObjectProperty<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(LazyLoadingBooleanProperty.class);
    public static final Boolean DEFAULT_LOADING_VALUE = Boolean.FALSE;
    public static final Boolean DEFAULT_ERROR_VALUE = Boolean.FALSE;

    public LazyLoadingBooleanProperty() {

	super();
    }

    public LazyLoadingBooleanProperty(final Boolean initialValue) {

	super(initialValue);
    }

    public LazyLoadingBooleanProperty(final Object bean, final String name, final Boolean initialValue) {

	super(bean, name, initialValue);
    }

    public LazyLoadingBooleanProperty(final Object bean, final String name) {

	super(bean, name);
    }

    @Override
    protected Boolean getFailedValue(final Throwable t) {

	if (logger.isErrorEnabled()) {
	    logger.error(t.getLocalizedMessage(), t);
	}
	return DEFAULT_ERROR_VALUE;
    }
}
