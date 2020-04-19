package serdes;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import types.HeartBeat;
import java.util.HashMap;
import java.util.Map;

public class AppSerdesHeartBeat extends Serdes {

    static final class HeartBeatSerde extends WrapperSerde<HeartBeat> {
        HeartBeatSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<HeartBeat> HeartBeat() {
        HeartBeatSerde serde = new HeartBeatSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, HeartBeat.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

}
