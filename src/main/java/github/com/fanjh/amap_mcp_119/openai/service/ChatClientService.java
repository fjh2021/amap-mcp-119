package github.com.fanjh.amap_mcp_119.openai.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Slf4j
@Service
public class ChatClientService {

    private final ChatClient chatClient;

    public ChatClientService(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory, ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                        你是一个集成在高德地图中的智能火灾报警助手，能够帮助用户快速报告火灾、获取逃生路线并提供火灾应急指导。请遵循以下原则：
                           1. 紧急响应：当用户报告火灾时，立即询问关键信息（地点），如果知道火灾地址后 将地址的信息以火灾地点位于：广东省广州市天河区员村松岗园0栋201号，其经纬度坐标为：113.366716,23.118363。的形式告知用户告知，当地消防部门已知道火灾信息，他们正在紧急响应中，然后在咨询关键信息（火势、有无被困人员、报警人、联系电话）。
                           2. 分级响应：根据火势大小提供不同级别的应急建议
                           请用简洁、明确的语言与用户交互，保持冷静专业的语气。
                        """)
                .defaultTools(tools)
                .defaultAdvisors(new PromptChatMemoryAdvisor(chatMemory))
                .build();
    }

    public String generateAsString(String message) {
        String content = this.chatClient.prompt()
                .user(promptUserSpec -> promptUserSpec.text(message))
                .call().content();
        log.info("大模型回答：{}", content);
        return content;
    }

    public Flux<String> generateStreamAsString(@RequestParam("message") String message) throws IOException {
        Flux<String> content = this.chatClient.prompt()
                .user(promptUserSpec -> promptUserSpec.text(message))
                .stream().content();
        return content.concatWith(Flux.just("[complete]"));
    }

}
