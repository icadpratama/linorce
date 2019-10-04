package com.lawencon.linov.outsource.payload.response;

public class ObjectResponse {
    private Object data;
    private int totalPages;
    private boolean last;
    private long totalElements;
    private int size;
    private int numberOfElements;
    private boolean first;
    private boolean empty;

    public ObjectResponse(Object data, int totalPages, boolean last, long totalElements, int size, int numberOfElements, boolean first, boolean empty) {
        this.data = data;
        this.totalPages = totalPages;
        this.last = last;
        this.totalElements = totalElements;
        this.size = size;
        this.numberOfElements = numberOfElements;
        this.first = first;
        this.empty = empty;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
