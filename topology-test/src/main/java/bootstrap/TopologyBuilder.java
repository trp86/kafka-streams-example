package bootstrap;

import serde.AppSerdes;
import types.AdClick;
import types.AdImpression;
import types.CampaignPerfomance;
import config.AppConfigs;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TopologyBuilder {
    private static final Logger logger = LogManager.getLogger();

    static void withBuilder(StreamsBuilder builder) {
        KStream<String, AdImpression> KS0 = builder.stream(AppConfigs.impressionTopic,
                Consumed.with(AppSerdes.String(), AppSerdes.AdImpression()));

        KTable<String, Long> adImpressionCount = KS0.groupBy((k, v) -> v.getCampaigner(),
                Grouped.with(AppSerdes.String(), AppSerdes.AdImpression()))
                .count();

        KStream<String, AdClick> KS1 = builder.stream(AppConfigs.clicksTopic,
                Consumed.with(AppSerdes.String(), AppSerdes.AdClick()));

        KTable<String, Long> adClickCount = KS1.groupBy((k, v) -> v.getCampaigner(),
                Grouped.with(AppSerdes.String(), AppSerdes.AdClick()))
                .count();

        KTable<String, CampaignPerfomance> campaignPerformance = adImpressionCount.leftJoin(
                adClickCount, (impCount, clkCount) -> new CampaignPerfomance()
                        .withAdImpressions(impCount)
                        .withAdClicks(clkCount))
                .mapValues((k, v) -> v.withCampaigner(k),
                        Materialized.<String, CampaignPerfomance, KeyValueStore<Bytes, byte[]>>
                                as(AppConfigs.stateStoreNameCP)
                                .withKeySerde(AppSerdes.String())
                                .withValueSerde(AppSerdes.CampaignPerfomance()));

        campaignPerformance.toStream().to(AppConfigs.outputTopic,
                Produced.with(AppSerdes.String(), AppSerdes.CampaignPerfomance()));
    }
}