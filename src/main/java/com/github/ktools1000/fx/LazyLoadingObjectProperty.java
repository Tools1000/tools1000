

package com.github.ktools1000.fx;

import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;

/**
 * A {@link SimpleObjectProperty} that lazily loads its value.
 *
 * @author kerner1000
 *
 * @param <T>
 *            type of the wrapped Object
 */
public abstract class LazyLoadingObjectProperty<T> extends AlwaysLoadingObjectProperty<T> {

    public LazyLoadingObjectProperty() {
	super();

    }

    public LazyLoadingObjectProperty(final Object bean, final String name, final T initialValue) {
	super(bean, name, initialValue);

    }

    public LazyLoadingObjectProperty(final Object bean, final String name) {
	super(bean, name);

    }

    public LazyLoadingObjectProperty(final T initialValue) {
	super(initialValue);

    }

    private boolean loaded = false;

    /**
     * Starts the loading task asynchronously (if not already running) and returns
     * {@link super#getValue()}.
     */
    @Override
    public T getValue() {
	if (!loaded) {
	    startLoadingService();
	}
	return super.getValue();
    }

    public boolean isLoaded() {
	return loaded;
    }

    public void setLoaded(final boolean loaded) {
	this.loaded = loaded;
    }

    /**
     * Starts the {@link Task} that will calculate this Property's value in the
     * background.
     */
    @Override
    protected void startLoadingService() {
	setLoaded(true);
	final Task<T> s = LazyLoadingObjectProperty.this.createTask();

	exe.submit(s);

	s.setOnFailed(e -> {
	    setValue(getFailedValue(e.getSource().getException()));
	    afterLoaded();
	});

	s.setOnSucceeded(e -> {
	    setValue(s.getValue());
	    afterLoaded();
	});
    }
}
