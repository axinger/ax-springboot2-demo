package com.ax.demo.strategy2;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StrategyFactoryImpl implements StrategyFactory {

    // 这里不要用注解
    private final Map<String, Strategy> strategyMap = new HashMap<>();

    public StrategyFactoryImpl(List<Strategy> strategyList) {
        this.strategyMap.clear();
        Map<String, Strategy> strategyMap = strategyList.stream()
                .collect(Collectors.toMap(Strategy::type, v -> v)); // List 中对象 用type 作为key, List中的对象 的 value 为对象本身时，
//                .collect(Collectors.toMap(Strategy::type, Function.identity()));
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }

    // Lombok注解-@SneakyThrows 直接上抛异常
    @SneakyThrows
    @Override
    public Strategy run(String type) {
        final Strategy strategy = this.strategyMap.get(type);
        if (strategy == null) {
            throw new Exception("type不对");
        }
        return strategy;
    }


}
