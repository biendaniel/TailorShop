package daniel.bien.tailor_shop.model.order;

import lombok.Data;

@Data
public class EmailParameters {
    String subject;
    String content;
    String recipient;
}
