
package com.github.tools1000.fx;

/**
 *
 * @author kerner1000
 *
 */
public abstract class LazyLoadingStringProperty extends LazyLoadingObjectProperty<String> {

    public static final String DEFAULT_LOADING_STRING = "Loading..";

    public LazyLoadingStringProperty(final Object bean, final String name, final String initialValue) {

	super(bean, name, initialValue);
    }

    public LazyLoadingStringProperty(final Object bean, final String name) {

	super(bean, name);
    }

    public LazyLoadingStringProperty(final String initialValue) {

	super(initialValue);
    }

    public LazyLoadingStringProperty() {

	this(DEFAULT_LOADING_STRING);
    }

    @Override
    protected String getFailedValue(final Throwable t) {

	return t.getLocalizedMessage();
    }
}
