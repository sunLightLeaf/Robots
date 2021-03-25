package ru.eatTheFrog.Robots.log;

import ru.eatTheFrog.Robots.ring_buffer.RingBufferQueue;
import java.util.*;

public class LogWindowSource
{
    private final int m_queueCapacity;

    private final RingBufferQueue<LogEntry> m_messagesQueue;
    private final Set<LogChangeListener> m_listeners;

    public LogWindowSource(int iQueueLength)
    {
        m_queueCapacity = iQueueLength;
        m_messagesQueue = new RingBufferQueue<>(m_queueCapacity);
        m_listeners = Collections.synchronizedSet(new HashSet<>());
    }

    public void registerListener(LogChangeListener listener)
    {
        m_listeners.add(listener);
    }

    public void unregisterListener(LogChangeListener listener)
    {
        if (m_listeners.contains(listener))
            m_listeners.remove(listener);
    }

    public void append(LogLevel logLevel, String strMessage)
    {
        LogEntry entry = new LogEntry(logLevel, strMessage);
        m_messagesQueue.push(entry);
        System.out.println(m_listeners.stream().count());
        synchronized (m_listeners) {
            m_listeners.forEach(LogChangeListener::onLogChanged);
        }

    }
    public int getCapacity() {
        return this.m_queueCapacity;
    }
    public int size()
    {
        return m_messagesQueue.getSize();
    }

    public Iterable<LogEntry> range(int startFrom, int count)
    {
        return (startFrom < 0 || startFrom >= m_messagesQueue.getSize())
                ? Collections.emptyList()
                : m_messagesQueue.getSlice(startFrom, count);
    }

    public Iterable<LogEntry> all()
    {
        return range(0, this.size());
    }
}
