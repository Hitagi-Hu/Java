package streamAPI;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

public class TestStreamAPI {
    @Test
    public void test() {
        Instant start = Instant.now();
        LongStream.rangeClosed(0, 100000000000L)
                .parallel()
                .reduce(0, Long::sum);

        Instant end = Instant.now();

        System.out.println("耗费时间为: " + Duration.between(start, end).toMillis() + " ms");
    }
}
