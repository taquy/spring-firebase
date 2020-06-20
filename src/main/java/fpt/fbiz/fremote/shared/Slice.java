package fpt.fbiz.fremote.shared;

import java.util.List;

public interface Slice<T> {
    int getNumber();
    int getSize();
    int getNumberOfElements();
    List<T> getContent();
}