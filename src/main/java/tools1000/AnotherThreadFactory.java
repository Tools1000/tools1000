package tools1000;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class AnotherThreadFactory implements ThreadFactory {
    private final int priority;
    private final boolean daemon;
    private final String namePrefix;
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public AnotherThreadFactory(final int priority) {
	this(priority, false);
    }

    public AnotherThreadFactory(final int priority, final boolean daemon) {
	this.priority = priority;
	this.daemon = daemon;
	namePrefix = "jobpool-" + poolNumber.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(final Runnable r) {
	final Thread t = new Thread(r, namePrefix + threadNumber.getAndIncrement());
	t.setDaemon(daemon);
	t.setPriority(priority);
	return t;
    }
}
