package github.com.fanjh.amap_mcp_119;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmapMcp119Application {
	@Bean
	public ChatMemory chatMemory() {
		return new InMemoryChatMemory();
	}

	public static void main(String[] args) {
		SpringApplication.run(AmapMcp119Application.class, args);
	}

}
