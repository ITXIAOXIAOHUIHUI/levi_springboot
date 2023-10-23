package com.springboot.levi.leviweb1.design.ServiceDeLocatorPattern;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.parser.Parser;

/**
 * @program: levi_springboot
 * @description:
 * @author: jhh
 * @create: 2023-02-27 10:30
 */
@Component("CSV")
public class CSVParser implements Parser {
    @Override
    public boolean checkEvent(Event.ID id) {
        return false;
    }

    @Override
    public Event peekEvent() {
        return null;
    }

    @Override
    public Event getEvent() {
        return null;
    }
}
