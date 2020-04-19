package sessionwindow;

import configs.AppConfig;
import serdes.AppSerdesUserClick;
import types.UserClicks;
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


public class CountingSessionApp {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.applicationID_sessionWindow);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfig.stateStoreName);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, UserClicks> KS0 = streamsBuilder.stream(AppConfig.userclick_topicName,
                Consumed.with(AppSerdesUserClick.String(), AppSerdesUserClick.UserClicks())
                        .withTimestampExtractor(new AppTimestampExtractor())
        );

        KTable<Windowed<String>, Long> KT01 =  KS0.groupByKey(Grouped.with(AppSerdesUserClick.String(),AppSerdesUserClick.UserClicks()))
                .windowedBy(SessionWindows.with(Duration.ofMinutes(5)))
                .count();

        KT01.toStream().foreach(
                (wKey, value) -> logger.info(
                        "User ID: " + wKey.key() + " Window ID: " + wKey.window().hashCode() +
                                " Window start: " + Instant.ofEpochMilli(wKey.window().start()).atOffset(ZoneOffset.UTC) +
                                " Window end: " + Instant.ofEpochMilli(wKey.window().end()).atOffset(ZoneOffset.UTC) +
                                " Count: " + value
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
