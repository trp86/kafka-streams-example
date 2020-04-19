package supressingwindow;

import serdes.AppSerdes;
import serdes.AppSerdesHeartBeat;
import types.HeartBeat;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Properties;
import configs.AppConfig;

public class WindowSuppress {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.applicationID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfig.stateStoreName);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, HeartBeat> KS0 = streamsBuilder.stream(AppConfig.heartBeatTopicName,
                Consumed.with(AppSerdesHeartBeat.String(), AppSerdesHeartBeat.HeartBeat())
                        .withTimestampExtractor(new AppTimestampExtractor())
        );


        KTable<Windowed<String>, Long> KT01 = KS0.groupByKey(Grouped.with(AppSerdesHeartBeat.String(), AppSerdesHeartBeat.HeartBeat()))
                .windowedBy(TimeWindows.of(Duration.ofSeconds(60)).grace(Duration.ofSeconds(10)))
                .count()
                .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded())); //If you want to supress a action until the window time ends.

        KT01.toStream().foreach(
                (wKey, value) -> System.out.println(
                        "App ID: " + wKey.key() + " Window ID: " + wKey.window().hashCode() +
                                " Window start: " + Instant.ofEpochMilli(wKey.window().start()).atOffset(ZoneOffset.UTC) +
                                " Window end: " + Instant.ofEpochMilli(wKey.window().end()).atOffset(ZoneOffset.UTC) +
                                " Count: " + value +
                                (value>2? " Application is Alive" : " Application Failed - Sending Alert Email...")
                )
        );

        logger.info("Starting Stream...");
        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Streams...");
            streams.close();
        }));

    }
}
