

package com.github.tools1000.fx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Task;

/**
 * A {@link SimpleObjectProperty} that loads its value on demand.
 *
 * @author kerner1000
 *
 * @param <T>
 *            type of the wrapped Object
 */
public abstract class AlwaysLoadingObjectProperty<T> extends SimpleObjectProperty<T> {

    protected static final ExecutorService exe = Executors
	    .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public AlwaysLoadingObjectProperty() {
	super();

    }

    public AlwaysLoadingObjectProperty(final Object bean, final String name, final T initialValue) {
	super(bean, name, initialValue);

    }

    public AlwaysLoadingObjectProperty(final Object bean, final String name) {
	super(bean, name);

    }

    public AlwaysLoadingObjectProperty(final T initialValue) {
	super(initialValue);

    }

    /**
     * Called after the background task's finished (success or failure). Override if
     * needed. E.g. to bind the value afterwards.
     */
    protected void afterLoaded() {

    }

    /**
     * Returns a {@link Task} that will calculate this Property's value in the
     * background.
     *
     * @return a {@link Task} that will calculate this Property's value in the
     *         background.
     */
    protected abstract Task<T> createTask();

    /**
     * Returns the value that should be set in case of failed loading.
     *
     * @param t
     *            the cause of failure
     * @return the value that should be set in case of failed loading
     */
    protected T getFailedValue(final Throwable t) {
	return null;
    }

    /**
     * Starts the loading task asynchronously and returns {@link super#getValue()}.
     */
    @Override
    public T getValue() {
	startLoadingService();
	return super.getValue();
    }

    @Override
    public void setValue(final T v) {
	super.setValue(v);
    }

    @Override
    public void set(final T newValue) {
	super.set(newValue);
    }

    /**
     * Starts the {@link Task} that will calculate this Property's value in the
     * background.
     */
    protected void startLoadingService() {
	final Task<T> s = AlwaysLoadingObjectProperty.this.createTask();

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
