package com.scm.scm20.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoargsConstructor
@AllArgsConstructor
public class message {
    private String content;
    @Builder.Default
    private MessageTypes type = MessageTypes.blue;


}
