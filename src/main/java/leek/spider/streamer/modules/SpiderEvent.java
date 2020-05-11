package leek.spider.streamer.modules;

import leek.spider.streamer.modules.enums.Indication;
import leek.spider.streamer.modules.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpiderEvent {
    private String id;
    private String name;
    private Type type;
    private Indication indication;
}
