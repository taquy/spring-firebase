package fpt.fbiz.fremote.shared;

public interface Page<T> extends Slice<T> {
    int getTotalPages();

    long getTotalElements();
}