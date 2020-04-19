package serdes;

import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import types.*;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {

    public static Serde<CreditCardTxn> CreditCardTxn() {
        Serde<CreditCardTxn> serde =  new SpecificAvroSerde<>();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("schema.registry.url", "http://localhost:8081");
        serde.configure(serdeConfigs, false);

        return serde;
    }

    public static Serde<LoggedIn> LoggedIn() {
        Serde<LoggedIn> serde =  new SpecificAvroSerde<>();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("schema.registry.url", "http://localhost:8081");
        serde.configure(serdeConfigs, false);

        return serde;
    }

    public static Serde<NotloggedIn> NotloggedIn() {
        Serde<NotloggedIn> serde =  new SpecificAvroSerde<>();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put("schema.registry.url", "http://localhost:8081");
        serde.configure(serdeConfigs, false);

        return serde;
    }



}