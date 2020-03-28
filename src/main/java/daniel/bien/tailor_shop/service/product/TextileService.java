package daniel.bien.tailor_shop.service.product;

import daniel.bien.tailor_shop.model.order.EmailParameters;
import daniel.bien.tailor_shop.model.product.Textile;
import daniel.bien.tailor_shop.model.product.Image;
import daniel.bien.tailor_shop.repository.product.ImageRepository;
import daniel.bien.tailor_shop.repository.product.TextileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextileService {

    private final TextileRepository textileRepository;
    private final ImageRepository imageRepository;
    private final ImageService imageService;

    @Autowired
    private EmailSender emailSender;

    public TextileService(TextileRepository textileRepository, ImageRepository imageRepository, ImageService imageService) {
        this.textileRepository = textileRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    public boolean isTextileExists(String name) {
        return textileRepository.checkIfTextileExists(name).isPresent();
    }

    public void addTextileWithImage(Textile textile) {
        Image image = new Image();
        image.setFileName(imageService.generateNameForTextileImage());
        imageRepository.save(image);
        textile.setMainImage(image);
        textileRepository.save(textile);
    }

    public void sendEmailToFactory(Integer id, Integer amount) {
        String content = "Witam, chciałbym zamówić tkaninę: " + id + " w ilości " + amount + "m. \n Pozdrawiam, ...";
        EmailParameters parameters = new EmailParameters();
        parameters.setContent(content);

        parameters.setRecipient("daniel.bien01@gmail.com");
        parameters.setSubject("Zamówienie tkaniny - zakład krawiecki");
        emailSender.sendEmail(parameters);
    }
}
