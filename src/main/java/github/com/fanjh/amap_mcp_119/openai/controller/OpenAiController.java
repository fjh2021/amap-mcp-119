package github.com.fanjh.amap_mcp_119.openai.controller;

import github.com.fanjh.amap_mcp_119.openai.service.ChatClientService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;

@CrossOrigin
@RestController
public class OpenAiController {
    @Resource
    private ChatClientService chatClientService;

    @GetMapping(value = "/ai/generateStreamAsString", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateStreamAsString(@RequestParam("message") String message) throws IOException {
        return chatClientService.generateStreamAsString(message);
    }

    @GetMapping(value = "/ai/generateAsString")
    public String generateAsString(@RequestParam("message") String message) {
        return chatClientService.generateAsString(message);
    }
}
