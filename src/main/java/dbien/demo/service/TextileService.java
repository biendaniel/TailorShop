package dbien.demo.service;

import dbien.demo.repository.TextileRepository;
import org.springframework.stereotype.Service;

@Service
public class TextileService {

    private final TextileRepository textileRepository;

    public TextileService(TextileRepository textileRepository) {
        this.textileRepository = textileRepository;
    }

    public boolean isTextileExists(String name) {
        return textileRepository.checkIfTextileExists(name).isPresent();
    }
}
