package com.github.axinger;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface TestChannel {
    @Output("output")
    MessageChannel output();
}
