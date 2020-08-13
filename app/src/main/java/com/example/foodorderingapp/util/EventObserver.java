package com.example.foodorderingapp.util;


import androidx.lifecycle.Observer;

public class EventObserver<T> implements Observer<Event<? extends T>> {

    public interface EventUnhandledContent<T> {
        void onEventUnhandledContent(T t);
    }

    private EventUnhandledContent<T> mContent;

    public EventObserver(EventUnhandledContent<T> content) {
        this.mContent = content;
    }

    @Override
    public void onChanged(Event<? extends T> event) {
        if (event != null) {
            T result = event.getContentIfNotHandled();
            if (result != null && mContent != null) {
                mContent.onEventUnhandledContent(result);
            }
        }
    }
}
