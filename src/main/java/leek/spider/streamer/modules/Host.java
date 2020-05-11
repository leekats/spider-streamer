package leek.spider.streamer.modules;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "hosts")
public class Host {
    private String ip;
    private Permissions permissions;
}
