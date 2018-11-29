package com.wey.juc_6.arraysum;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @author Yale.Wei
 * @date 2018/10/28 14:14
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5,time = 1)
@Measurement(iterations = 5,time = 1)
@Fork(1)
@State(Scope.Benchmark)
public class SumBenchmark {

}
