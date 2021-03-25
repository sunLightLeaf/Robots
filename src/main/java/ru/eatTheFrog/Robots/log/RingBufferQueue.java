package ru.eatTheFrog.Robots.ring_buffer;

import java.util.*;

public class RingBufferQueue<T> {
    private int capacity;
    private int size = 0;
    private ArrayList<T> buffer;
    private int tail = 0;
    private int head = 0;

    public RingBufferQueue(int capacity) {
        this.capacity = capacity;
        this.buffer = new ArrayList<>(Collections.nCopies(capacity, null));
    }
    public int getSize() {
        return this.size;
    }
    public int incrementPointer(int ptr) {
        if (ptr == this.capacity - 1)
            return 0;
        return ptr + 1;
    }

    public void push(T element) {

        if (size == this.capacity) {
            head = incrementPointer(head);
        } else {
            size++;
        }
        this.buffer.set(tail, element);
        tail = incrementPointer(tail);
    }
    public List<T> getBufferCopy() {
        return new ArrayList<>(this.buffer);
    }
    public T pop() {
        if (size == 0)
            return null;
        T elem = this.buffer.get(head);
        head = incrementPointer(head);
        size--;
        return elem;
    }
    public List<T> getSlice(int start, int count) {
        List<T> sliceList = new ArrayList<>();
        int slicePtr = this.head + start;
        int realPtr = start;
        for (int i = 0; i < count; i++) {
            if (realPtr >= this.size)
                break;
            sliceList.add(this.buffer.get(slicePtr));
            slicePtr = incrementPointer(slicePtr);
            realPtr++;
        }
        return sliceList;
    }
}
