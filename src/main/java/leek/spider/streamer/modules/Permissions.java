package leek.spider.streamer.modules;

import leek.spider.streamer.modules.enums.Indication;
import leek.spider.streamer.modules.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Permissions {
    private Indication indication;
    private Type type;
}