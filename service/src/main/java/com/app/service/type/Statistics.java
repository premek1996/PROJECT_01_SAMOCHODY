package com.app.service.type;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;

import java.math.BigDecimal;
import java.util.IntSummaryStatistics;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Statistics {

    private final BigDecimal min;
    private final BigDecimal avg;
    private final BigDecimal max;

    public static Statistics toStatistics(IntSummaryStatistics intSummaryStatistics) {
        return new Statistics(
                BigDecimal.valueOf(intSummaryStatistics.getMin()),
                BigDecimal.valueOf(intSummaryStatistics.getAverage()),
                BigDecimal.valueOf(intSummaryStatistics.getMax())
        );
    }

    public static Statistics toStatistics(BigDecimalSummaryStatistics bigDecimalSummaryStatistics) {
        return new Statistics(
                bigDecimalSummaryStatistics.getMin(),
                bigDecimalSummaryStatistics.getAverage(),
                bigDecimalSummaryStatistics.getMax()
        );
    }
}
